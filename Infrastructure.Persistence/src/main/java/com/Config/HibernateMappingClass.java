package com.Config;

import Enitities.*;

public class HibernateMappingClass {
    public static Class[] initialize() {
        return
                new Class[]{
                        ArimaDiscoveryEntity.class,

                };
    }
}
