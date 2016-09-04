package ventas.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

/**
 * Specific implementation of LazyDataModel supporting the facade pattern
 *
 * @param <T> Entity to be used for the lazy model
 */
public class LazyEntityDataModel<T> extends LazyDataModel<T> {

    private final AbstractFacade<T> facade;
    private List<T> itemList;

    /**
     * Loads data lazily using only one sort field.
     *
     * @param first
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @param filters
     * @return
     */
    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        if (this.facade != null) { // Handle data that needs to be retrieved from the data back-end of the application

            String sortOrderName = sortOrder.toString();
            this.itemList = this.facade.findRange(first, pageSize, sortField, sortOrderName, filters);
            this.setRowCount(this.facade.count(filters)); // Count ALL records for the applied filter
            return this.itemList;

        } else if (this.itemList != null) { // Handle data that was passed in by application

            // filter
            List<T> filteredItemList = filter(this.itemList, filters);

            // sort
            if (sortField != null) {
                Collections.sort(filteredItemList, new LazyEntitySorter<T>(sortField, sortOrder));
            }

            // rowCount
            int itemCount = filteredItemList.size();
            this.setRowCount(itemCount);

            // paginate
            if (itemCount > pageSize) {
                try {
                    return filteredItemList.subList(first, first + pageSize);
                } catch (IndexOutOfBoundsException e) {
                    return filteredItemList.subList(first, first + (itemCount % pageSize));
                }
            } else {
                return filteredItemList;
            }

        } else { // Nothing passed in by application
            return null;
        }
    }

    /**
     * Loads data lazily using multiple sort fields.
     *
     * @param first
     * @param pageSize
     * @param multiSortMeta
     * @param filters
     * @return
     */
    @Override
    public List<T> load(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        // Turn sort info into a linked hash map for the facade
        HashMap<String, String> sortFields = new LinkedHashMap<>();
        if (multiSortMeta != null) {
            for (SortMeta s : multiSortMeta) {
                sortFields.put(s.getSortField(), s.getSortOrder().toString());
            }
        }
        itemList = this.facade.findRange(first, pageSize, sortFields, filters);
        this.setRowCount(this.facade.count(filters)); // Count ALL records for the applied filter
        return itemList;
    }

    /**
     * Retrieves an entity's Row Key by returning a String representation of
     * hashCode. Note: Instead of trying to identify the ACTUAL key field or
     * fields in this generic method, the code depends on the hashCode method of
     * the entity, which has typically been generated by NetBeans and combines
     * the key fields into a hash number! This is supposed to be a unique number
     * according to the hashCode JavaDoc entry. If you are using a PrimeFaces
     * DataTable with Lazy Loading enabled, please DO NOT specify the "rowKey"
     * attribute, which the CRUD Generator normally adds. It will break getting
     * the RowData which depends on the getRowKey method's return value of a
     * hashCode. However, if "rowKey" is specified on the DataTable, THIS method
     * (getRowKey) will not be called. I hope this is clear to understand.
     *
     * @param object
     * @return
     */
    @Override
    public Object getRowKey(T object) {
        return Integer.toString(object.hashCode());
    }

    /**
     * Retrieves the entity based on a given Row Key.
     *
     * @param rowKey
     * @return
     */
    @Override
    public T getRowData(String rowKey) {
        if (itemList != null) {
            for (T entity : itemList) {
                if (getRowKey(entity).equals(rowKey)) {
                    return entity;
                }
            }
        }
        return null;
    }

    /**
     *
     * @param facade
     */
    public LazyEntityDataModel(AbstractFacade<T> facade) {
        super();
        this.facade = facade;
        this.itemList = null;
    }

    public LazyEntityDataModel(List<T> itemList) {
        super();
        this.facade = null;
        this.itemList = itemList;
    }

    private List<T> filter(List<T> itemList, Map<String, Object> filters) {

        List<T> filteredItemList = new ArrayList<>();

        // apply filters
        for (T entity : itemList) {
            boolean match = true;
            for (String filterField : filters.keySet()) {
                String filterValue = String.valueOf(filters.get(filterField)).toLowerCase();
                String fieldValue = String.valueOf(EntityUtility.getFieldValue(entity, filterField)).toLowerCase();
                if (filterValue != null && fieldValue != null && !fieldValue.startsWith(filterValue)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                filteredItemList.add(entity);
            }
        }
        return filteredItemList;
    }

}
