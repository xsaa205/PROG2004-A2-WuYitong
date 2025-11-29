package util;

import model.Visitor;
import java.util.Comparator;

/**
 * Part4B要求：实现Comparator<Visitor>，按至少2个实例变量排序
 * 长隆场景适配：排序规则贴合长隆运营优先级（快速通道>VIP>家庭>普通，早入园优先）
 */
public class VisitorComparator implements Comparator<Visitor> {

    @Override
    public int compare(Visitor v1, Visitor v2) {
        // 1. 第一排序维度：入园日期升序（早入园的访客排在前，符合长隆"先到先体验"原则）
        int dateCompare = v1.getVisitDate().compareTo(v2.getVisitDate());
        if (dateCompare != 0) {
            return dateCompare; // 日期不同时，直接按日期排序
        }

        // 2. 第二排序维度：访客类型降序（同日期下，长隆运营优先级：快速通道 > VIP > 家庭 > 普通）
        // 自定义类型权重：通过字符串对比实现优先级（权重高的类型字符串靠后，用v2.compareTo(v1)实现降序）
        return getVisitorTypeWeight(v2.getVisitorType()) - getVisitorTypeWeight(v1.getVisitorType());
    }

    /**
     * 长隆访客类型权重：为不同类型分配数值，便于排序（权重越高，优先级越高）
     * @param visitorType 访客类型（如"快速通道访客"）
     * @return 对应权重（10=快速通道，8=VIP，5=家庭，3=普通）
     */
    private int getVisitorTypeWeight(String visitorType) {
        return switch (visitorType) {
            case "快速通道访客" -> 10; // 长隆快速通道最高优先级
            case "VIP访客" -> 8;       // VIP次之
            case "家庭访客" -> 5;       // 家庭访客（带儿童）优先于普通
            case "普通访客" -> 3;       // 普通访客基础优先级
            default -> 1;              // 未知类型最低优先级
        };
    }
}