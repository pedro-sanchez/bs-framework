package br.com.bs.fw.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import br.com.bs.fw.enumeration.FilterType;

public class GenericSearch<T> {
	
	private StringBuilder query;
	private Boolean count = Boolean.FALSE;
	private List<SelectItem> filters;
	private Map<String, FilterType> filterTypes;
	
	private String selectedFilter;
	private String filter;
		
	public StringBuilder getSearch(Boolean count){
		this.count = count;
		this.query = new StringBuilder();
		builAllSearch();
		
		return query;
	}
	
	public void builAllSearch(){
		buildQuery();
		buildWhere();
		buildGroupBy();
		buildOrderBy();
	}
	
	public void buildQuery(){		

		if(count){
			query.append("select count(e.id) from ");
		}
		else{
			query.append("select e from ");			
		}
		query.append(ReflectionUtil.getTClass(getClass()).getSimpleName());
		query.append(" e ");

		
	}
	
	public void buildWhere(){	

		if(filter != null && !filter.isEmpty()){
					
			FilterType filterType = filterTypes.get(selectedFilter);
			query.append("where ");
			if(FilterType.Long.equals(filterType)){
				query.append(" e.");
				query.append(selectedFilter);
				query.append(" = ");
				query.append(filter);
				query.append(" ");
				
			}
			else if (FilterType.String.equals(filterType)) {
				query.append(" LOWER(e.");
				query.append(selectedFilter);
				query.append(") like '%");
				query.append(filter.toLowerCase());
				query.append("%' ");
			}	
			
		}
		
		
	}
	
	public void buildGroupBy(){		
		
	}
	
	public void buildOrderBy(){	
		if(!count){
			query.append(" order by e.id ");
		}
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}
	
	public void addSelectFilter(String filterKey, String filterLabel, FilterType filterType){
		if(filters == null || filters.isEmpty()){
			filters = new ArrayList<>();
			filterTypes = new HashMap<>();
			selectedFilter = filterKey;
		}		
		filterTypes.put(filterKey, filterType);
		filters.add(new SelectItem(filterKey, filterLabel));
	}

	public List<SelectItem> getFilters() {
		return filters;
	}

	public void setFilters(List<SelectItem> filters) {
		this.filters = filters;
	}

	public String getSelectedFilter() {
		return selectedFilter;
	}

	public void setSelectedFilter(String selectedFilter) {
		this.selectedFilter = selectedFilter;
	}
	
	
	
	
}
