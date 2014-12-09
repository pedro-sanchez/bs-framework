package br.com.bs.fw.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;

import br.com.bs.fw.annotation.Name;

public class SearchUtil {
    public static Map<String, String> getIdName(Class<?> clazz){
        Map<String, String> mapa = new HashMap<>();
        for(Field field: clazz.getDeclaredFields()){
            try {
                if(field.isAnnotationPresent(Id.class)){
                    mapa.put("id", field.getName());
                }
                if(field.isAnnotationPresent(Name.class)){
                    mapa.put("name", field.getName());
                }

            } catch (Exception e) {
                throw new RuntimeException("Problema ao gerar o mapa",e);
            }
        }
        return mapa;
    }

    public static StringBuilder queryFindByReduce(Class<?> clazz, String alias){

        StringBuilder sql = new StringBuilder();
        Map<String, String> fieldMap = getIdName(clazz);
        Boolean useAlias =  alias != null && !fieldMap.isEmpty();

        if (fieldMap == null || fieldMap.isEmpty()) {
            return sql;
        }

        sql.append("select new ");
        sql.append(clazz.getName());
        sql.append("(");

        if (fieldMap.containsKey("id")) {
            if(useAlias){
                sql.append(alias).append(".");
            }
            sql.append(fieldMap.get("id"));
        }
        if (fieldMap.size()>1){
            sql.append(",");
        }
        if (fieldMap.containsKey("name")) {
            if(useAlias){
                sql.append(alias).append(".");
            }
            sql.append(fieldMap.get("name"));
        }
        sql.append(") from ");
        sql.append(clazz.getName());
        if(useAlias){
            sql.append(" ").append(alias);
        }

        return sql;

    }

    public static StringBuilder queryFindByReduce(Class<?> clazz){
        return queryFindByReduce(clazz, null);
    }
}
