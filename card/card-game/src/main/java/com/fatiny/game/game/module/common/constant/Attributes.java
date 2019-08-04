package com.fatiny.game.game.module.common.constant;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 属性
 */
@Slf4j
public class Attributes {
	
	public static final Attributes EMPTY = new Attributes();
	
	public static final int IdPropertyHp = 201; // 兵力
	public static final int IdPropertyAtk = 202; // 攻击
	public static final int IdPropertySpeed = 203; // 攻速
	public static final int IdPropertyRage = 204; // 聚气
	public static final int IdPropertyAdDef = 205; // 物防
	public static final int IdPropertyApDef = 206; // 法防

	public static final int IdPropertyHit = 207; // 命中（率）
	public static final int IdPropertyDodge = 208; // 闪躲（率）
	public static final int IdPropertyCrit = 209; // 暴击（率）
	public static final int IdPropertyDeCrit = 210; // 韧性（率）
	public static final int IdPropertyParry = 211; // 格挡（率）
	public static final int IdPropertyDeParry = 212; // 破击（率）

	public static final int IdPropertyDeDam = 213; // 减伤
	public static final int IdPropertyAddDam = 214; // 必杀
	public static final int IdPropertyDeCritValue = 215;// 强韧
	public static final int IdPropertyCare = 216; // 疗效
	public static final int IdPropertyPlusDam = 217; // 附伤
	public static final int IdPropertyPlusDamRate = 218; // 附伤系数

	public static final int IdPropertyDeDamRate = 219; // 减伤系数
	public static final int IdPropertyCareRate = 220; // 疗效系数
	public static final int IdPropertyRageSpd = 221; // 聚气速度

	public static final int IdPropertyHpPercent = 231; // 兵力%
	public static final int IdPropertyAtkPercent = 232; // 攻击%
	public static final int IdPropertySpeedPercent = 233; // 攻速%

	public static final int IdPropertyRagePercent = 234; // 聚气%
	public static final int IdPropertyAdDefPercent = 235; // 物防%
	public static final int IdPropertyApDefPercent = 236; // 法防%
	public static final int IdPropertyHitPercent = 237; // 命中（率）%
	public static final int IdPropertyDodgePercent = 238; // 闪躲（率）%
	public static final int IdPropertyCritPercent = 239; // 暴击（率）%

	public static final int IdPropertyDeCritPercent = 240; // 韧性（率）%
	public static final int IdPropertyParryPercent = 241; // 格挡（率）%
	public static final int IdPropertyDeParryPercent = 242; // 破击（率）%
	public static final int IdPropertyDeDamPercent = 243; // 减伤%
	public static final int IdPropertyAddDamPercent = 244; // 必杀%
	public static final int IdPropertyDeCritValuePercent = 245; // 强韧%

	public static final int IdPropertyCarePercent = 246; // 疗效%
	public static final int IdPropertyPlusDamPercent = 247; // 附伤%
	public static final int IdPropertyPlusDamRatePercent = 248; // 附伤系数%
	public static final int IdPropertyDeDamRatePercent = 249; // 减伤系数%
	public static final int IdPropertyCareRatePercent = 250; // 疗效系数%
	public static final int IdPropertyRageSpdPercent = 251; // 聚气速度%

	public static final float IdRatioHP = 0.1f; // 兵力
	public static final float IdRatioAtk = 0.5f; // 攻击
	public static final float IdRatioRage = 4f; // 聚气
	public static final float IdRatioAdDef = 0.75f; // 物防
	public static final float IdRatioApDef = 0.75f; // 法防
	public static final float IdRatioHit = 1.5f; // 命中
	public static final float IdRatioDodge = 1.5f; // 闪躲

	public static final float IdRatioCrit = 1.5f; // 暴击
	public static final float IdRatioDeCrit = 1.5f; // 韧性
	public static final float IdRatioParry = 1.5f; // 格挡
	public static final float IdRatioDeParry = 1.5f; // 破击
	public static final float IdRatioPlusDam = 0.4f; // 附伤
	public static final float IdRatioDeDam = 0.4f; // 减伤
	
	/** {属性ID=属性值} */
	private Map<Integer, Integer> attrs = new HashMap<>();
	/** 附带信息 */
	private Object attachment;
	
	public Attributes() {
		
	}
	
	/**
	 * 计算战力
	 */
	public int calFC(Map<Integer, Integer> attrs) {
		float hp = attrs.getOrDefault(IdPropertyHp, 0) * (100 + attrs.getOrDefault(IdPropertyHpPercent, 0)) / 100f * IdRatioHP;
		float atk = attrs.getOrDefault(IdPropertyAtk, 0) * (100 + attrs.getOrDefault(IdPropertyAtkPercent, 0)) / 100f * IdRatioAtk;
		float rage = attrs.getOrDefault(IdPropertyRage, 0) * (100 + attrs.getOrDefault(IdPropertyRagePercent, 0)) / 100f * IdRatioRage;
		float adDef = attrs.getOrDefault(IdPropertyAdDef, 0) * (100 + attrs.getOrDefault(IdPropertyAdDefPercent, 0)) / 100f * IdRatioAdDef;
		float apDef = attrs.getOrDefault(IdPropertyApDef, 0) * (100 + attrs.getOrDefault(IdPropertyApDefPercent, 0)) / 100f * IdRatioApDef;
		float hit = attrs.getOrDefault(IdPropertyHit, 0) * (100 + attrs.getOrDefault(IdPropertyHitPercent, 0)) / 100f * IdRatioHit;
		float dodge = attrs.getOrDefault(IdPropertyDodge, 0) * (100 + attrs.getOrDefault(IdPropertyDodgePercent, 0)) / 100f * IdRatioDodge;

		float crit = attrs.getOrDefault(IdPropertyCrit, 0) * (100 + attrs.getOrDefault(IdPropertyCritPercent, 0)) / 100f * IdRatioCrit;
		float deCrit = attrs.getOrDefault(IdPropertyDeCrit, 0) * (100 + attrs.getOrDefault(IdPropertyDeCritPercent, 0)) / 100f * IdRatioDeCrit;
		float parry = attrs.getOrDefault(IdPropertyParry, 0) * (100 + attrs.getOrDefault(IdPropertyParryPercent, 0)) / 100f * IdRatioParry;
		float deParry = attrs.getOrDefault(IdPropertyDeParry, 0) * (100 + attrs.getOrDefault(IdPropertyDeParryPercent, 0)) / 100f * IdRatioDeParry;
		float plusDam = attrs.getOrDefault(IdPropertyPlusDam, 0) * (100 + attrs.getOrDefault(IdPropertyPlusDamPercent, 0)) / 100f * IdRatioPlusDam;
		float deDam = attrs.getOrDefault(IdPropertyDeDam, 0) * (100 + attrs.getOrDefault(IdPropertyDeDamPercent, 0)) / 100f * IdRatioDeDam;

		int fc = (int) (hp + atk + rage + adDef + apDef + hit + dodge + crit + deCrit + parry + deParry + plusDam + deDam);
		return fc;
	}
	
	
	/**
	 * value 已经默认 *100
	 * @param formatAttrs  统一格式(支持Float) : 1,202|2,40|3,26|4,26|5,26|6,19|27,1.5
	 */
	public Attributes(String formatAttrs) {
		analyze(formatAttrs);
	}
	
	// 统一格式
	// 1,202|2,40|3,26|4,26|5,26|6,19|27,1
	private void analyze(String formatAttrs) {
		String[] splitArray = StringUtils.split(formatAttrs, "\\|");
		if (splitArray != null) {
			for (String element : splitArray) {
				String[] attrKeyVal = StringUtils.split(element, ",");
				if (attrKeyVal.length != 2) {
					log.error("Attributes解析出错:{}", formatAttrs);
				}
				Integer attrKey = Integer.valueOf(attrKeyVal[0].trim());
				Integer attrVal = (int)(Float.valueOf(attrKeyVal[1].trim()) * 100);
				attrs.put(attrKey, attrVal);
			}
		}
	}
	
	// 1,202|2,40|3,26|4,26|5,26|6,19|27,1
	private void analyze(String formatAttrs, float ratio) {
		String[] splitArray = StringUtils.split(formatAttrs, "\\|");
		if (splitArray != null) {
			for (String element : splitArray) {
				String[] attrKeyVal = StringUtils.split(element, ",");
				if (attrKeyVal.length != 2) {
					log.error("Attributes解析出错:{}", formatAttrs);
				}

				Integer attrKey = Integer.valueOf(attrKeyVal[0].trim());
				Float attrVal = Float.valueOf(attrKeyVal[1].trim());
				addAttr(attrKey, attrVal, ratio);
			}
		}
	}
	
	public void addAttrs(String formatAttrs) {
		analyze(formatAttrs);
	}
	
	public void addAttrs(String formatAttrs, float ratio) {
		analyze(formatAttrs, ratio);
	}
	
	public void addAttrs(Attributes attributes) {
		addAttrs(attributes.get());
	}
	
	public void replaceAttrs(Attributes attributes) {
		replaceAttrs(attributes.get());
	}
	
	/**
	 * 添加属性值，value 默认 *100
	 * @param key {@link Attributes}
	 * @param value
	 */
	public void addAttr(int key, int value) {
		increaseAttr(key, value * 100);
	}
	
	/**
	 * 添加属性值，value 默认 *100
	 * @param key {@link Attributes}
	 * @param value
	 * @param ratio 加成比例
	 */
	public void addAttr(int key, float value, float ratio) {
		if (ratio <= 0.0f) {
			ratio = 1.0f;
		}
		increaseAttr(key, (int)(value * 100 * ratio));
	}
	
	/**
	 * 添加属性值，value 默认 *100
	 * @param key {@link Attributes}
	 * @param value
	 */
	public void addAttr(int key, float value) {
		increaseAttr(key, (int)(value * 100));
	}
	
	/**
	 * 覆盖属性值
	 * 
	 * @param key    属性KEY
	 * @param value  原值
	 */
	public void setAttr(int key, int value) {
		attrs.put(key, value * 100);
	}
	
	/**
	 * 覆盖属性值
	 * 
	 * @param key    属性KEY
	 * @param value  原值
	 */
	public void setAttr(int key, float value) {
		attrs.put(key, (int) (value * 100));
	}
	
	
	/**
	 * 这个方法覆盖原有的属性值
	 * @param key
	 * @param value  传入的值要  *100
	 */
	@Deprecated
	public void putAttr(int key, float value) {
		attrs.put(key, (int)value);
	}
	
	//加属性
	private void addAttrs(Map<Integer, Integer> plusAttrs) {
		for (Map.Entry<Integer, Integer> entry : plusAttrs.entrySet()) {
			Integer attrKey = entry.getKey();
			Integer plusVal = entry.getValue();
			increaseAttr(attrKey, plusVal);
		}
	}
	
	
	private void increaseAttr(int attrKey, int plusVal) {
	    // 策划有些属性是需要减, 可传入负数
		Integer oldVal = attrs.get(attrKey);
		int newVal = oldVal == null ? plusVal : plusVal + oldVal;
		attrs.put(attrKey, newVal);
	}
	
	/**
	 * 替换相同的属性
	 * @param plusAttrs
	 */
	private void replaceAttrs(Map<Integer, Integer> plusAttrs) {
		for (Map.Entry<Integer, Integer> entry : plusAttrs.entrySet()) {
			Integer attrKey = entry.getKey();
			Integer plusVal = entry.getValue();
			attrs.put(attrKey, plusVal);
		}
	}
	
	/**
	 * 计算的值是基础属性值
	 * 
	 * @param attrKey
	 * @param reduceVal
	 */
	public void decreaseAttr(int attrKey, int reduceVal) {
		if (reduceVal < 0) return; // 不允许传负值
		Integer value = attrs.get(attrKey);
		if (value != null) {
			value -= reduceVal * 100; // *100
		}
		attrs.put(attrKey, Math.max(0, value));
	}
	
	
	public Map<Integer, Integer> get() {
		return attrs;
	}
	
	
	public int getValue(int attrKey) {
		Integer value = attrs.get(attrKey);
		return value == null ? 0 : value / 100;
	}
	
	
	public float getFloatVal(int attrKey) {
		Integer value = attrs.get(attrKey);
		return value == null ? 0 : value.floatValue() / 100;
	}
	
	/**
	 * 清空属性集
	 */
	public void clear() {
		attrs.clear();
	}
	
	
	public Attributes copy() {
		Attributes copy = new Attributes();
		copy.attrs.putAll(this.attrs);
		return copy;
	}
	
}
