package com.boc.common.core.mybatis.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MybatisHepler {

	public static void main(String[] args) {
//		String sql = "SELECT DISTINCT CAST(TA.ID AS CHAR) AS adviserId, TA.`NAME` AS adviserName, TA.HEADER AS adviserImg, TA.BNAME AS busName, TA.LEVEL AS level, TA.LEVELNAME AS levelName, TA.HONOR AS honor, TA.HONORNAME AS honorName, TA.PHONE AS phone, TA.ADVVOMULE AS advVomule, TA.CONSULTATION AS consultaTion, TA.SCORE AS score, TA.CONTENT AS content, TBSE.TYPE AS busType FROM pt_business.pt_business_site_spu BS LEFT JOIN pt_business.pt_business_adviser TA ON TA.SID = BS.SID LEFT JOIN pt_business.pt_business_seller TBSE ON TBSE.SELLERID = TA.BID WHERE BS.STATE = '1' AND BS.SITEID = ? AND TA.STATE = '1'"
//				+ " AND  NOT exists ( SELECT 1 FROM pt_business.pt_business_adviser_spuwhite TAS WHERE  ta.ID= tas.adviserid  and TAS.SPUID = BS.SPUID AND TAS.SID = BS.SID ) ORDER BY TA.`LEVEL` = 0, TA.`LEVEL` DESC, TA.REFRESH_TIME IS NULL, TA.REFRESH_TIME DESC, TA.SCORE DESC, TA.ADVVOMULE DESC, TA.CONSULTATION DESC ";
		String sql = "SELECT 	CAST(TBSS.SPUID AS CHAR) AS spuId, 	TSP.`NAME` AS goodsName, 	TSP.BANNER AS banner, 	TSP.ATTENTION AS attention, 	TSP.VOMULE AS vomule, 	TSP.SCORE AS goodsScore, 	CAST(FS.skuId AS CHAR) AS skuId, 	FS.priceSku, 	FS.priceOfMarket, 	FS.proId, 	FS.priceOfSale, 	FS.type, 	FS.beginTime, 	FS.endTime, 	DATE_FORMAT(NOW(), '%Y%m%d%H%i%s') AS systemTime, 	FS.inventoryOfSurplus FROM 	pt_business.pt_business_adviser TBADV LEFT JOIN pt_business.pt_business_site_spu TBSS ON TBSS.BID = TBADV.BID AND TBSS.SID = TBADV.SID LEFT JOIN pt_goods.pt_goods_spu TSP ON TSP.ID = TBSS.SPUID LEFT JOIN ( 	SELECT 		GS.* 	FROM 		( 			SELECT 				OTHER.* 			FROM 				( 					SELECT 						TGS.ID AS skuId, 						TGS.SPUID AS spuId, 						TGS.PRICE AS priceOfMarket, 						TOPS.PID AS proId, 						TOPS.PRICEOFSALE AS priceOfSale, 						TP.TYPE AS type, 						TP.BEGINTIME AS beginTime, 						TP.ENDTIME AS endTime, 						TOPS.INVENTORYOFSURPLUS AS inventoryOfSurplus,  					IF ( 						TSPU.ALLOWSELFPRICE = 1,  					IF ( 						TC.MODIFYPRICE = 0,  					IF ( 						TSKU.PRICEOFB IS NULL, 						TGS.PRICE, 						TSKU.PRICEOFB 					),  				IF ( 					PA.PRICEOFA IS NULL,  				IF ( 					TSKU.PRICEOFB IS NULL, 					TGS.PRICE, 					TSKU.PRICEOFB 				), 				PA.PRICEOFA 				) 					), 					TGS.PRICE 					) AS priceSku 					FROM 						pt_business.pt_business_site_spu KA 					LEFT JOIN pt_goods.pt_goods_sku TGS ON TGS.SPUID = KA.SPUID 					LEFT JOIN pt_goods.pt_goods_spu TSPU ON TSPU.ID = TGS.SPUID 					LEFT JOIN pt_operate.pt_operate_promotion_sku TOPS ON TOPS.SKUID = TGS.ID 					AND TOPS.STATE = '1' 					AND TOPS.PID IN ( 						SELECT 							TOP.ID 						FROM 							pt_operate.pt_operate_promotion_adviser PROAD 						LEFT JOIN pt_operate.pt_operate_promotion TOP ON PROAD.PID = TOP.ID 						AND '01' IN (TOP.TERMINAL) -- 活动终端类型 						AND ( 							( 								TOP.STATE = '5' 								AND TOP.BEGINTIME <= 20170527152035 -- 活动时间判断 								AND TOP.ENDTIME > 20170527152035 							) 							OR ( 								TOP.STATE = '3' 								AND TOP.BEGINTIME <= 20170527152035 -- 活动时间判断 								AND TOP.ENDTIME > 20170527152035 -- 活动时间判断 							) 						) 						WHERE 							PROAD.AID = 866911031098740736 						AND PROAD.STATE = '1' 					) 					LEFT JOIN pt_operate.pt_operate_promotion TP ON TP.ID = TOPS.PID 					AND 					IF ( 						TP.TYPE = '3', 						TOPS.INVENTORYOFSURPLUS > 0, 						1 = 1 					) 					AND '01' IN (TP.TERMINAL) -- 活动终端类型 					LEFT JOIN pt_business.pt_business_adviser TA ON TA.SID = KA.SID 					LEFT JOIN pt_business.pt_business_spu_confirm TC ON TC.SPUID = TGS.SPUID 					AND ( 						TC.SID = TA.SID 						OR TC.SID IS NULL 					) 					LEFT JOIN pt_business.pt_business_skuprice TSKU ON TSKU.SKUID = TGS.ID 					AND ( 						TSKU.SID = TA.SID 						OR TSKU.SID IS NULL 					) 					LEFT JOIN pt_business.pt_business_adviser_skuprice PA ON PA.SKUID = TGS.ID 					AND PA.AID = TA.ID 					AND ( 						PA.SID = TA.SID 						OR PA.SID IS NULL 					) 					WHERE 						TGS.STATE = '1' 					AND KA.BID = 863930221374607360 					AND KA.SITEID = '100000' 					AND KA.STATE = '1' -- 顾问商家编号和站点信息 					AND TA.ID = 866911031098740736 				) AS OTHER 			ORDER BY 				OTHER.endTime IS NULL, 				OTHER.type = 4, 				OTHER.type DESC, 				OTHER.endTime ASC, 				OTHER.priceOfSale ASC, 				OTHER.priceSku = - 1, 				OTHER.priceSku ASC 		) AS GS 	GROUP BY 		GS.spuId ) AS FS ON FS.spuId = TBSS.SPUID WHERE 	TBSS.STATE = '1' AND TBADV.ID NOT IN ( 	SELECT 		TAS.ADVISERID 	FROM 		pt_business.pt_business_adviser_spuwhite TAS 	WHERE 		TAS.SPUID = TBSS.SPUID 	AND TAS.SID = TBSS.SID 	AND TAS.SID = TBADV.SID ) AND TBADV.ID = 866911031098740736 -- 顾问编号 AND skuId IS NOT NULL AND TBSS.SITEID = '100000' -- 站点 GROUP BY 	TBSS.SPUID ORDER BY 	priceOfSale IS NULL, 	FS.type = 4, 	FS.type DESC, 	priceOfSale ASC, 	priceSku IS NULL, 	priceSku = - 1, 	priceSku ASC, 	skuId ASC";
		long t = System.currentTimeMillis();
		System.out.println(new MybatisHepler().getCountSql(sql));
		System.out.println(System.currentTimeMillis()-t);
		System.out.println(new MybatisHepler().getPagingSql(sql,0,10));
	}

	/**
	 * 获取查询总数对应的SQL
	 * 
	 * @param querySelect
	 * @return
	 */
	public String getCountSql(String querySelect) {

		querySelect = compress(querySelect);
		int orderIndex = getLastOrderInsertPoint(querySelect);

		int formIndex = getAfterFormInsertPoint(querySelect);
		String select = querySelect.substring(0, formIndex);

		// 如果SELECT 中包含 DISTINCT 只能在外层包含COUNT
		if (select.toLowerCase().indexOf("select distinct") != -1
				|| querySelect.toLowerCase().indexOf("group by") != -1) {
			return new StringBuffer(querySelect.length()).append("select count(1) count from (")
					.append(querySelect.substring(0, orderIndex)).append(" ) t").toString();
		} else {
			return new StringBuffer(querySelect.length()).append("select count(1) count ")
					.append(querySelect.substring(formIndex, orderIndex)).toString();
		}
	}

	/**
	 * 得到最后一个Order By的插入点位置
	 * 
	 * @return 返回最后一个Order By插入点的位置
	 */
	private int getLastOrderInsertPoint(String querySelect) {
		int orderIndex = querySelect.toLowerCase().lastIndexOf("order by");
		if (orderIndex == -1) {
			orderIndex = querySelect.length();
		} else {
			if (!isBracketCanPartnership(querySelect.substring(orderIndex, querySelect.length()))) {
				throw new RuntimeException("My SQL 分页必须要有Order by 语句!");
			}
		}
		return orderIndex;
	}

	/**
	 * 将{@code paging}转换为{@link org.apache.ibatis.session.RowBounds}对象
	 * 
	 * @param paging
	 * @return
	 */
	// public final RowBounds toRowBounds(Paging paging) {
	// return new RowBounds(paging.getRowOffset(), paging.getPageSize());
	// }

	/**
	 * 得到分页的SQL
	 * 
	 * @param offset
	 *            偏移量
	 * @param limit
	 *            位置
	 * @return 分页SQL
	 */
	public String getPagingSql(String querySelect, int offset, int limit) {

		querySelect = compress(querySelect);

		String sql = querySelect.replaceAll("[^\\s,]+\\.", "") + " limit " + offset + " ," + limit;

		return sql;

	}

	/**
	 * 将SQL语句压缩成一条语句，并且每个单词的间隔都是1个空格
	 * 
	 * @param sql
	 *            SQL语句
	 * @return 如果sql是NULL返回空，否则返回转化后的SQL
	 */
	private String compress(String sql) {
		return sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");
	}

	/**
	 * 得到SQL第一个正确的FROM的的插入点
	 */
	private int getAfterFormInsertPoint(String querySelect) {
		String regex = "\\s+FROM\\s+";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(querySelect);
		while (matcher.find()) {
			int fromStartIndex = matcher.start(0);
			String text = querySelect.substring(0, fromStartIndex);
			if (isBracketCanPartnership(text)) {
				return fromStartIndex;
			}
		}
		return 0;
	}

	/**
	 * 判断括号"()"是否匹配,并不会判断排列顺序是否正确
	 * 
	 * @param text
	 *            要判断的文本
	 * @return 如果匹配返回TRUE, 否则返回FALSE
	 */
	private boolean isBracketCanPartnership(String text) {
		if (text == null || (getIndexOfCount(text, '(') != getIndexOfCount(text, ')'))) {
			return false;
		}
		return true;
	}

	/**
	 * 得到一个字符在另一个字符串中出现的次数
	 * 
	 * @param text
	 *            文本
	 * @param ch
	 *            字符
	 */
	private int getIndexOfCount(String text, char ch) {
		int count = 0;
		for (int i = 0; i < text.length(); i++) {
			count = (text.charAt(i) == ch) ? count + 1 : count;
		}
		return count;
	}
}
