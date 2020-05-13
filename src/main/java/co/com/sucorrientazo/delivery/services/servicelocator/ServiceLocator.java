package co.com.sucorrientazo.delivery.services.servicelocator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceLocator {
    private static final Logger LOGGER = Logger.getLogger("ServiceLocator");

    private Map<Class<?>, Map<String, Object>> serviceRegistry = new HashMap<>();

    private static ServiceLocator serviceLocator;

    private ServiceLocator(){}

    public static ServiceLocator getInstance(){
        if(Objects.isNull(serviceLocator)){
            serviceLocator = new ServiceLocator();
        }
        return serviceLocator;
    }

    public <T> void registerService(Class<T> interfaceType, String qualifier, Object serviceObjet){

        Map<String, Object> serviceOfSameTypeMap = serviceRegistry.get(interfaceType);
        if(Objects.nonNull(serviceOfSameTypeMap)){
            serviceRegistry.get(interfaceType).put(qualifier, serviceObjet);
        }else {
            serviceOfSameTypeMap = new HashMap<>();
            serviceOfSameTypeMap.put(qualifier, serviceObjet);
            serviceRegistry.put(interfaceType, serviceOfSameTypeMap);
        }
    }

    public <T> T getService(Class<T> interfaceType, String qualifier){
        Map<String, Object> serviceOfSameTypeMap = serviceRegistry.get(interfaceType);
        if(Objects.nonNull(serviceOfSameTypeMap)){
            T service = (T) serviceOfSameTypeMap.get(qualifier);
            if(Objects.isNull(service))LOGGER.log(Level.SEVERE, "Service with qualifier {} does not exist", qualifier);
            return service;
        }
        LOGGER.log(Level.SEVERE, "Service of type {} does not exist", interfaceType);
        return null;
    }
}
