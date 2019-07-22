/**
 * Description: 
 * @author 李泽良
 * @date 2018年6月12日
 */
package com.utils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * Description: 
 * @author 李泽良
 * @date 2018年6月12日
 */
public class CollectorsUtil{
	public static final Set<Collector.Characteristics> CH_NOID = Collections.emptySet();
	
	/**
	 * 实例：
	 * List<Peron> list = new ArrayList<>();
	 * Person person = new Person();
	 * person.setId(1);
	 * person.setName("张三");
	 * person.setAmount("200");
	 * list.add(person);
	 * 获取id集合
	 * List<Integer> idList = CollectorsUtil.toList(list, Person::getId);
	 * 获取金额大于100的id集合
	 * List<Integer> idList = CollectorsUtil.toList(list, person -> person.getAmount > 100, Person::getId);
	 * 获取name的set集合
	 * Set<String> nameSet = CollectorsUtil.toSet(list, Person::getName);
	 * id为键，name为值得map集合
	 * Map<Integer, String> map = CollectorsUtil.toMap(list, Person::getId, Person::getName);
	 * name进行分组
	 * Map<String, List<Person>> group = CollectorsUtil.groupBy(list, Person::getName);
	 * amount求和（BigDecimal）有相应的long，double，int版本
	 * BigDecimal sumAmount = CollectorsUtil.summingBigDecimalBy(list, Person::getAmount)
	 * name为键，Person对象的map集合
	 * Map<String, Person> mapByName = CollectorsUtil.list2Map(list, Person::getName);
	 */
	
	/**
	 * Description: 解决空指针异常，如果origin.getName()不为空，将origin的name赋给target的name属性
	 * 替代 if(origin.getName() != null){target.setName(origin.getName())}  或   target.setName(origin.getName() != null ? origin.getName() : null)
	 * Person origin = new Person("张三")
	 * Person target = new Person();
	 * CollectorsUtil.notNull(origin::getName, target::setName);
	 * @param t
	 * @param consumer void
	 * @author 李泽良
	 * @date 2018年6月29日
	 */
	public static <T> void notNull(Supplier<? extends T> origin, Consumer<? super T> target){
		Optional.ofNullable(origin.get()).ifPresent(target);
	}
	/**
	 * Description: 同上，重载版本
	 * name = null;
	 * Person target = new Person();
	 * CollectorsUtil.notNull(name, target::setName);
	 * @param t
	 * @param consumer void
	 * @author 李泽良
	 * @date 2018年6月29日
	 */
	public static <T> void notNull(T t, Consumer<? super T> consumer){
		Optional.ofNullable(t).ifPresent(consumer);
	}
	
	/**
	 * Description: 转换List  
	 * List<Integer> idList = CollectorsUtil.toList(list, Person::getId);
	 * List<Integer> idList = CollectorsUtil.toList(list, person -> person.getId());（另一种调用方式，以下皆适用）		
	 * @param collection 
	 * @param mapper      
	 * @return List<R>     
	 * @author 李泽良
	 * @date 2018年6月26日
	 */
	public static <T, R> List<R> toList(Collection<T> collection, Function<? super T, ? extends R> mapper) {
		return collection.parallelStream().map(mapper).collect(Collectors.toList());
	}
	
	/**
	 * Description: 转换list+过滤    获取所有金额大于100的id集合
	 * List<Integer> idList = CollectorsUtil.toList(list, person -> person.getAmount > 100, Person::getId);（以下皆适用）
	 * @param collection
	 * @param predicate
	 * @param mapper
	 * @return List<R>
	 * @author 李泽良
	 * @date 2018年6月28日
	 */
	public static <T, R> List<R> toList(Collection<T> collection, Predicate<? super T> predicate, Function<? super T, ? extends R> mapper) {
		return collection.parallelStream().filter(predicate).map(mapper).collect(Collectors.toList());
	}
	
	/**
	 * Description: 转化Set
	 * Set<String> nameSet = CollectorsUtil.toSet(list, Person::getName);
	 * @param collection 
	 * @param mapper      	  
	 * @return Set<R>         
	 * @author 李泽良
	 * @date 2018年6月26日
	 */
	public static <T, R> Set<R> toSet(Collection<T> collection, Function<? super T, ? extends R> mapper) {
		return collection.parallelStream().map(mapper).collect(Collectors.toSet());
	}
	
	/**
	 * Description: 转化Set+过滤
	 * @param collection
	 * @param mapper     
	 * @return Set<R>    
	 * @author 李泽良
	 * @date 2018年6月28日
	 */
	public static <T, R> Set<R> toSet(Collection<T> collection, Predicate<? super T> predicate, Function<? super T, ? extends R> mapper) {
		return collection.parallelStream().filter(predicate).map(mapper).collect(Collectors.toSet());
	}
	
	/**
	 * Description: 转换Map，id为键，name为值
	 * Map<Integer, String> map = CollectorsUtil.toMap(list, Person::getId, Person::getName);
	 * @param collection 
	 * @param mapper      	  
	 * @return Map<K, V>      
	 * @author 李泽良
	 * @date 2018年6月26日
	 */
	public static <T, K, V> Map<K, V> toMap(Collection<T> collection, Function<? super T, ? extends K> key, Function<? super T, ? extends V> value) {
		return collection.parallelStream().collect(Collectors.toMap(key, value));
	}
	
	/**
	 * Description: 转换Map+过滤
	 * @param collection 
	 * @param mapper      	  
	 * @return Map<K, V>      
	 * @author 李泽良
	 * @date 2018年6月28日
	 */
	public static <T, K, V> Map<K, V> toMap(Collection<T> collection, Predicate<? super T> predicate, Function<? super T, ? extends K> key, Function<? super T, ? extends V> value) {
		return collection.parallelStream().filter(predicate).collect(Collectors.toMap(key, value));
	}
	
	
	/**
	 * Description: 分组，以name分组
	 * Map<String, List<Person>> map = CollectorsUtil.groupBy(list, Person::getName);
	 * @param collection 
	 * @param mapper     
	 * @return Map<R, List<T> 
	 * @author 李泽良
	 * @date 2018年6月26日
	 */
	public static <T, R> Map<R, List<T>> groupBy(Collection<T> collection, Function<? super T, ? extends R> mapper) {
		return collection.parallelStream().collect(groupingBy(mapper));
	}
	
	/**
	 * Description: 分组+过滤
	 * @param collection 
	 * @param mapper     
	 * @return Map<R, List<T> 
	 * @author 李泽良
	 * @date 2018年6月28日
	 */
	public static <T, R> Map<R, List<T>> groupBy(Collection<T> collection, Predicate<? super T> predicate, Function<? super T, ? extends R> mapper) {
		return collection.parallelStream().filter(predicate).collect(groupingBy(mapper));
	}
	
	/**
	 * Description: 多级分组，以班级，姓名分组
	 * Map<String, Map<String, Person>> = CollectorsUtil.groupMoreBy(list, Person::getClazz, Person::getName)
	 * @param collection 
	 * @param mapper      
	 * @return Map<String, Map<String, Integer>>--->Map<班级, Map<姓名, Person>>
	 * @author 李泽良
	 * @date 2018年6月27日
	 */
	public static <T, R, B> Map<R, Map<B, List<T>>> groupMoreBy(Collection<T> collection, Function<? super T, ? extends R> mapper1, Function<? super T, ? extends B> mapper2) {
		return collection.parallelStream().collect(groupingBy(mapper1, groupingBy(mapper2)));
	}
	
	/**
	 * Description: 多级分组 +过滤 ----> 筛选五年级的男生，并以班级，姓名分组
	 * Map<String, Map<String, Person>> = CollectorsUtil.groupMoreBy(list, person -> person.getClazz == '五年级' && person.getSex = '男', Person::getClazz, Person::getName)
	 * @param collection     
	 * @param mapper    
	 * @return Map<String, Map<String, Integer>>--->Map<班级, Map<姓名, Person>>            
	 * @author 李泽良
	 * @date 2018年6月28日
	 */
	public static <T, R, B> Map<R, Map<B, List<T>>> groupMoreBy(Collection<T> collection, Predicate<? super T> predicate, Function<? super T, ? extends R> mapper1, Function<? super T, ? extends B> mapper2) {
		return collection.parallelStream().filter(predicate).collect(groupingBy(mapper1, groupingBy(mapper2)));
	}
	
	/**
	 * Description: Double类型求和，此处指amount为Double类型
	 * Double d = CollectorsUtil.summingDoubleBy(list, Person::getAmount);
	 * @param collection 
	 * @param mapper     
	 * @return BigDecimal 
	 * @author 李泽良
	 * @date 2018年6月27日
	 */
	public static <T, R> Double summingDoubleBy(Collection<T> collection, ToDoubleFunction<? super T> mapper) {
		return collection.parallelStream().collect(Collectors.summingDouble(mapper));
	}
	
	/**
	 * Description: Long类型求和 ，此处指amount为Long类型
	 * Long d = CollectorsUtil.summingLongBy(list, Person::getAmount);
	 * @param collection 
	 * @param mapper     
	 * @return BigDecimal 
	 * @author 李泽良
	 * @date 2018年6月27日
	 */
	public static <T, R> Long summingLongBy(Collection<T> collection, ToLongFunction<? super T> mapper) {
		return collection.parallelStream().collect(Collectors.summingLong(mapper));
	}
	
	/**
	 * Description: int类型求和  ，此处指amount为int类型，同上
	 * @param collection 
	 * @param mapper     
	 * @return BigDecimal 
	 * @author 李泽良
	 * @date 2018年6月27日
	 */
	public static <T, R> Integer summingIntBy(Collection<T> collection, ToIntFunction<? super T> mapper) {
		return collection.parallelStream().collect(Collectors.summingInt(mapper));
	}
	
	/**
	 * Description: BigDecimal求和 ，此处指amount为BigDecimal类型，同上
	 * @param collection 
	 * @param mapper     
	 * @return BigDecimal 
	 * @author 李泽良
	 * @date 2018年6月27日
	 */
	public static <T> BigDecimal summingBigDecimalBy(Collection<T> collection, ToBigDecimalFunction<? super T> mapper) {
		return collection.parallelStream().collect(summingBigDecimal(mapper));
	}
	/**
	 * Description: BigDecimal求和+过滤
	 * @param collection 
	 * @param mapper     
	 * @return BigDecimal 
	 * @author 李泽良
	 * @date 2018年6月27日
	 */
	public static <T> BigDecimal summingBigDecimalBy(Collection<T> collection, Predicate<? super T> predicate, ToBigDecimalFunction<? super T> mapper) {
		return collection.parallelStream().filter(predicate).collect(summingBigDecimal(mapper));
	}
	/**
	 * Description: 分组求和   以name分组，并求出amount总和，amount为BigDecimal
	 * Map<String, BigDecimal> map = CollectorsUtil.groupAndSummingBy(list, Person::getName, Person::getAmount); 
	 * @param collection
	 * @param mapper1
	 * @param mapper2
	 * @return Map<R,BigDecimal>
	 * @author 李泽良
	 * @date 2018年6月28日
	 */
	public static <T, R> Map<R, BigDecimal> groupAndSummingBigDecimalBy(Collection<T> collection, Function<? super T, ? extends R> mapper1, ToBigDecimalFunction<? super T> mapper2){
		return collection.parallelStream().collect(groupingBy(mapper1, summingBigDecimal(mapper2)));
	}
	
	/**
	 * Description: 分组求和+过滤
	 * @param collection
	 * @param mapper1
	 * @param mapper2
	 * @return Map<R,BigDecimal>
	 * @author 李泽良
	 * @date 2018年6月28日
	 */
	public static <T, R> Map<R, BigDecimal> groupAndSummingBigDecimalBy(Collection<T> collection, Predicate<? super T> predicate, Function<? super T, ? extends R> mapper1, ToBigDecimalFunction<? super T> mapper2){
		return collection.parallelStream().filter(predicate).collect(groupingBy(mapper1, summingBigDecimal(mapper2)));
	}
	
	/**
	 * Description: 分组求和，amount为double
	 * Map<String, BigDecimal> map = CollectorsUtil.groupAndSummingBy(list, Person::getName, Person::getAmount); 
	 * @param collection
	 * @param mapper1
	 * @param mapper2
	 * @return Map<R,BigDecimal>
	 * @author 李泽良
	 * @date 2018年6月28日
	 */
	public static <T, R> Map<R, Double> groupAndSummingDoubleBy(Collection<T> collection, Function<? super T, ? extends R> mapper1, ToDoubleFunction<? super T> mapper2){
		return collection.parallelStream().collect(groupingBy(mapper1, Collectors.summingDouble(mapper2)));
	}
	
	/**
	 * Description: 分组求和+过滤
	 * @param collection
	 * @param mapper1
	 * @param mapper2
	 * @return Map<R,BigDecimal>
	 * @author 李泽良
	 * @date 2018年6月28日
	 */
	public static <T, R> Map<R, Double> groupAndSummingDoubleBy(Collection<T> collection, Predicate<? super T> predicate, Function<? super T, ? extends R> mapper1, ToDoubleFunction<? super T> mapper2){
		return collection.parallelStream().filter(predicate).collect(groupingBy(mapper1, Collectors.summingDouble(mapper2)));
	}
	
	/**
	 * Description: 分组求和，amount为Long
	 * Map<String, BigDecimal> map = CollectorsUtil.groupAndSummingBy(list, Person::getName, Person::getAmount); 
	 * @param collection
	 * @param mapper1
	 * @param mapper2
	 * @return Map<R,BigDecimal>
	 * @author 李泽良
	 * @date 2018年6月28日
	 */
	public static <T, R> Map<R, Long> groupAndSummingLongBy(Collection<T> collection, Function<? super T, ? extends R> mapper1, ToLongFunction<? super T> mapper2){
		return collection.parallelStream().collect(groupingBy(mapper1, Collectors.summingLong(mapper2)));
	}
	
	/**
	 * Description: 分组求和+过滤
	 * @param collection
	 * @param mapper1
	 * @param mapper2
	 * @return Map<R,BigDecimal>
	 * @author 李泽良
	 * @date 2018年6月28日
	 */
	public static <T, R> Map<R, Long> groupAndSummingLongBy(Collection<T> collection, Predicate<? super T> predicate, Function<? super T, ? extends R> mapper1, ToLongFunction<? super T> mapper2){
		return collection.parallelStream().filter(predicate).collect(groupingBy(mapper1, Collectors.summingLong(mapper2)));
	}
	
	/**
	 * Description: 分组求和，amount为int
	 * Map<String, BigDecimal> map = CollectorsUtil.groupAndSummingBy(list, Person::getName, Person::getAmount); 
	 * @param collection
	 * @param mapper1
	 * @param mapper2
	 * @return Map<R,BigDecimal>
	 * @author 李泽良
	 * @date 2018年6月28日
	 */
	public static <T, R> Map<R, Integer> groupAndSummingIntBy(Collection<T> collection, Function<? super T, ? extends R> mapper1, ToIntFunction<? super T> mapper2){
		return collection.parallelStream().collect(groupingBy(mapper1, Collectors.summingInt(mapper2)));
	}
	
	/**
	 * Description: 分组求和+过滤
	 * @param collection
	 * @param mapper1
	 * @param mapper2
	 * @return Map<R,BigDecimal>
	 * @author 李泽良
	 * @date 2018年6月28日
	 */
	public static <T, R> Map<R, Integer> groupAndSummingIntBy(Collection<T> collection, Predicate<? super T> predicate, Function<? super T, ? extends R> mapper1, ToIntFunction<? super T> mapper2){
		return collection.parallelStream().filter(predicate).collect(groupingBy(mapper1, Collectors.summingInt(mapper2)));
	}

	/**
	 * Description: 以某字段为键，对象为值，与groupBy差异不大，区别是，map的值一个为对象，一个为List
	 * Map<String, Person> mapByName = CollectorsUtil.list2Map(list, Person::getName);
	 * @param collection
	 * @param mapper
	 * @return Map<R,T>
	 * @author 李泽良
	 * @date 2018年6月28日
	 */
	public static <T, R> Map<R, T> list2Map(Collection<T> collection, Function<? super T, ? extends R> mapper){
		return collection.parallelStream().collect(Collectors.toMap(mapper, Function.identity()));
	}
	
	/**
	 * Description: 同上+过滤
	 * @param collection
	 * @param mapper
	 * @return Map<R,T>
	 * @author 李泽良
	 * @date 2018年6月28日
	 */
	public static <T, R> Map<R, T> list2Map(Collection<T> collection, Predicate<? super T> predicate, Function<? super T, ? extends R> mapper){
		return collection.parallelStream().filter(predicate).collect(Collectors.toMap(mapper, Function.identity()));
	}
	
	/**
	 * Description: 逗号分隔
	 * String names = CollectorsUtil.join(list, Person::getName);
	 * @param collection
	 * @param mapper
	 * @return String
	 * @author 李泽良
	 * @date 2018年6月29日
	 */
	public static <T, R> String join(Collection<T> collection, Function<? super T, ? extends R> mapper){
		return collection.parallelStream().map(Object::toString).collect(Collectors.joining(", "));
	}
	
	
	/**
	 * Description: 逗号分隔+过滤
	 * @param collection
	 * @param mapper
	 * @return String
	 * @author 李泽良
	 * @date 2018年6月29日
	 */
	public static <T, R> String join(Collection<T> collection, Predicate<? super T> predicate, Function<? super T, ? extends R> mapper){
		return collection.parallelStream().filter(predicate).map(Object::toString).collect(Collectors.joining(", "));
	}
	
	/**
	 * Description: 逗号分隔
	 * String names = CollectorsUtil.join(list);
	 * @param collection
	 * @param mapper
	 * @return String
	 * @author 李泽良
	 * @date 2018年6月29日
	 */
	public static <T> String join(Collection<T> collection){
		return collection.parallelStream().map(Object::toString).collect(Collectors.joining(", "));
	}
	
	/**
	 * Description: 指定分隔符，
	 * String names = CollectorsUtil.join(list, "-");
	 * @param collection
	 * @param mapper
	 * @return String
	 * @author 李泽良
	 * @date 2018年6月29日
	 */
	public static <T> String join(Collection<T> collection, String split){
		return collection.parallelStream().map(Object::toString).collect(Collectors.joining(split));
	}
	
	/**
	 * Description: 指定分隔符，
	 * String names = CollectorsUtil.join(list, Person::getName, "-");
	 * @param collection
	 * @param mapper
	 * @return String
	 * @author 李泽良
	 * @date 2018年6月29日
	 */
	public static <T, R> String join(Collection<T> collection, Function<? super T, ? extends R> mapper, String split){
		return collection.parallelStream().map(Object::toString).collect(Collectors.joining(split));
	}
	
	/**
	 * Description: 指定分隔符+过滤
	 * @param collection
	 * @param mapper
	 * @return String
	 * @author 李泽良
	 * @date 2018年6月29日
	 */
	public static <T, R> String join(Collection<T> collection, Predicate<? super T> predicate, Function<? super T, ? extends R> mapper, String split){
		return collection.parallelStream().filter(predicate).map(Object::toString).collect(Collectors.joining(split));
	}
	
	
	
	private CollectorsUtil() {
	}

	@SuppressWarnings("unchecked")
	private static <I, R> Function<I, R> castingIdentity() {
		return i -> (R) i;
	}

	static class CollectorImpl<T, A, R> implements Collector<T, A, R> {
		private final Supplier<A> supplier;
		private final BiConsumer<A, T> accumulator;
		private final BinaryOperator<A> combiner;
		private final Function<A, R> finisher;
		private final Set<Characteristics> characteristics;

		CollectorImpl(Supplier<A> supplier, BiConsumer<A, T> accumulator, BinaryOperator<A> combiner,
				Function<A, R> finisher, Set<Characteristics> characteristics) {
			this.supplier = supplier;
			this.accumulator = accumulator;
			this.combiner = combiner;
			this.finisher = finisher;
			this.characteristics = characteristics;
		}

		CollectorImpl(Supplier<A> supplier, BiConsumer<A, T> accumulator, BinaryOperator<A> combiner,
				Set<Characteristics> characteristics) {
			this(supplier, accumulator, combiner, castingIdentity(), characteristics);
		}

		@Override
		public BiConsumer<A, T> accumulator() {
			return accumulator;
		}

		@Override
		public Supplier<A> supplier() {
			return supplier;
		}

		@Override
		public BinaryOperator<A> combiner() {
			return combiner;
		}

		@Override
		public Function<A, R> finisher() {
			return finisher;
		}

		@Override
		public Set<Characteristics> characteristics() {
			return characteristics;
		}
	}

	/**
	 * Description: 根据BigDecimal求和
	 * @param mapper  例如：  Person::getAmount || person -> person.getAmount()
	 * @return Collector<T,?,BigDecimal>
	 * @author 李泽良
	 * @date 2018年6月28日
	 */
	public static <T> Collector<T, ?, BigDecimal> summingBigDecimal(ToBigDecimalFunction<? super T> mapper) {
		return new CollectorImpl<>(() -> new BigDecimal[1], (a, t) -> {
			if (a[0] == null) {
				a[0] = BigDecimal.ZERO;
			}
			a[0] = a[0].add(mapper.applyAsBigDecimal(t));
		}, (a, b) -> {
			a[0] = a[0].add(b[0]);
			return a;
		}, a -> a[0], CH_NOID);
	}
}
