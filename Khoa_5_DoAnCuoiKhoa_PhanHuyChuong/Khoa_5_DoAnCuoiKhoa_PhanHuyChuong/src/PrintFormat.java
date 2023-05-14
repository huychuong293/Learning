
/**
* Người tạo: Phan Huy Chương
* Ngày tạo: 25/7/2022
* Version 1.0
 */
public class PrintFormat {

	public static String formatNumCell(Number num) {
		String paddLeft = "%5s";
		String paddRight = "%-10s";
		return String.format(paddLeft, " ") + String.format(paddRight, num);
	}

	public static String formatTextCell(String text) {
		String paddLeft = "%-25s";
		return String.format(paddLeft, " " + text);
	}

	public static String formatLongTextCell(String text) {
		String paddLeft = "%-40s";
		return String.format(paddLeft, " " + text);
	}

}
