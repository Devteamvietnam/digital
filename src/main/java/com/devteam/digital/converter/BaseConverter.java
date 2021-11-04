package com.devteam.digital.converter;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public class BaseConverter {

    public static Converter<String, UUID> uuidConverter = ctx -> ctx.getSource()!=null? UUID.fromString(ctx.getSource()) : null;
    protected ModelMapper mapper= new ModelMapper();

    public <T> T map(Object src, Class<T> className) {
        return mapId(src, mapper.map(src, className));
    }

    @SuppressWarnings("unchecked")
    protected <T> T mapId(Object src, Object dest) {
		/*try {
			String methodName = "getId";
			Method getIdMethod = src.getClass().getMethod(methodName);
			Object value = getIdMethod.invoke(src);
			methodName = "setId";
			if(value!=null) {
				if(value instanceof UUID) {
					Method setIdMethod = dest.getClass().getMethod(methodName, String.class);
					setIdMethod.invoke(dest, ((UUID) value).toString());
				} else if(value instanceof String){
					if(!((String) value).isEmpty()) {
						Method setIdMethod = dest.getClass().getMethod(methodName, UUID.class);
						setIdMethod.invoke(dest, UUID.fromString(((String) value)));
					}
				}
			}

		}catch(Exception ex) {
			ex.printStackTrace();
		}*/
        return (T) dest;
    }

    public <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }


}
