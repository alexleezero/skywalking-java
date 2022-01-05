package org.apache.skywalking.apm.plugin.pt.commons.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lijian
 * @since 2022/1/5
 */
public final class StrUtil {

	public static String wildcard2RegexPattern(String wildcard) {
		int i;
		StringBuilder sb = new StringBuilder();
		while ((i = wildcard.indexOf("*")) != -1) {
			sb.append(wildcard, 0, i);
			if (i == 0 || wildcard.charAt(i - 1) != '.') {
				sb.append(".*");
			} else {
				sb.append('*');
			}
			wildcard = wildcard.substring(i + 1);
		}
		sb.append(wildcard);
		return sb.toString();
	}

	public static boolean wildcardMatch(String wildcard, String target) {
		if (target == null || "".equals(target)) {
			return false;
		}
		String regex = wildcard2RegexPattern(wildcard);
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(target);
		return matcher.matches();
	}

}
