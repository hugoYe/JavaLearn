package com.system.common.support;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class XBeanUtil extends BeanUtils {

    private static final String JAVA_CLASS = "class";
    private static final String JAVA_SERIA_VERSION_UID = "serialVersionUID";

    /**
     * 对象间属性信息copy
     *
     * @param dest：目标对象
     * @param orig：源对象
     * @param copyNull:是否复制NULL属性，true:复制 false:不复制
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void copyProperties(Object dest, Object orig, boolean copyNull)
            throws IllegalAccessException, InvocationTargetException {
        copyProperties(dest, orig, copyNull, true, null);
    }

    public static void copyPropertiesIgnoresEmpty(Object dest, Object orig)
            throws IllegalAccessException, InvocationTargetException {
        copyProperties(dest, orig, false, false, null);
    }

    /**
     * 对象间属性信息copy
     *
     * @param dest：目标对象
     * @param orig：源对象
     * @param copyNull:是否复制NULL属性，true:复制 false:不复制
     * @param fieldIgnores                忽略的字段数组
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @SuppressWarnings("rawtypes")
    public static void copyProperties(Object dest, Object orig, boolean copyNull, boolean copyEmpty,
                                      String[] fieldIgnores) throws IllegalAccessException, InvocationTargetException {
        // Validate existence of the specified beans
        if (dest == null) {
            throw new IllegalArgumentException("No destination bean specified");
        }
        if (orig == null) {
            throw new IllegalArgumentException("No origin bean specified");
        }

        // 是map对象
        if (orig instanceof Map) {
            Map origMap = (Map) orig;
            Iterator names = origMap.keySet().iterator();
            while (names.hasNext()) {
                String name = names.next().toString();
                if (JAVA_CLASS.equals(name) || JAVA_SERIA_VERSION_UID.equals(name)) {
                    continue;
                }
                Object value = origMap.get(name);
                boolean canCopyValue = canCopy(value, name, copyNull, copyEmpty, fieldIgnores);
                if (canCopyValue) {
                    copyProperty(dest, name, value);
                }
            }
            return;
        }

        PropertyDescriptor[] origDescriptors = PropertyUtils.getPropertyDescriptors(orig);
        for (int i = 0; i < origDescriptors.length; i++) {
            if (origDescriptors[i].getReadMethod() == null) {
                continue;
            }
            String name = origDescriptors[i].getName();
            if (JAVA_CLASS.equals(name) || JAVA_SERIA_VERSION_UID.equals(name)) {
                continue;
            }
            Object value = null;
            try {
                value = PropertyUtils.getSimpleProperty(orig, name);
            } catch (NoSuchMethodException e) {
                value = null;
            }
            boolean canCopyValue = canCopy(value, name, copyNull, copyEmpty, fieldIgnores);
            if (canCopyValue) {
                copyProperty(dest, name, value);
            }
        }
    }

    private static boolean canCopy(Object value, String fiedName, boolean copyNull, boolean copyEmpty,
                                   String[] fieldIgnores) {
        // (1) fieldIgnores 包含了字段直接不能copy
        if (ArrayUtils.contains(fieldIgnores, fiedName)) {
            return false;
        }

        // 当不允许复制null，但字段值为null时，直接false
        if (false == copyNull && value == null) {
            return false;
        }

        // 当不允许复制""，但字段值为""时，直接false
        if (value instanceof String && false == copyEmpty && StringUtils.isEmpty(value.toString())) {
            return false;
        }

        // 当不允许复制 空 Collection，但字段值为 空 Collection时，直接false
        if (value instanceof Collection && false == copyEmpty && CollectionUtils.isEmpty((Collection) value)) {
            return false;
        }

        // 当不允许复制 空 Map，但字段值为 空 Map时，直接false
        if (value instanceof Map && false == copyEmpty && MapUtils.isEmpty((Map) value)) {
            return false;
        }

        return true;
    }

}