package com.design;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2019-03-15
 * Time: 20:40
 * Description: 枚举实现，该实现可以防止反射攻击
 */
public enum EnumSingleton {

    INSTANCE;

    private String objName;


    public String getObjName() {
        return objName;
    }


    public void setObjName(String objName) {
        this.objName = objName;
    }


    public static void main(String[] args) {

        // 单例测试
        EnumSingleton firstSingleton = EnumSingleton.INSTANCE;
        firstSingleton.setObjName("firstName");
        System.out.println(firstSingleton.getObjName());
        EnumSingleton secondSingleton = EnumSingleton.INSTANCE;
        secondSingleton.setObjName("secondName");
        System.out.println(firstSingleton.getObjName());
        System.out.println(secondSingleton.getObjName());

        // 反射获取实例测试
        try {
            EnumSingleton[] enumConstants = EnumSingleton.class.getEnumConstants();
            for (EnumSingleton enumConstant : enumConstants) {
                System.out.println(enumConstant.getObjName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
