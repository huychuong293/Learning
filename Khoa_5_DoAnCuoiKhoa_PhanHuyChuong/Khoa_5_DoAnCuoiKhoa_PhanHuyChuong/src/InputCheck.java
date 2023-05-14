/**
* Mục đích: kiểm tra dữ liệu nhập vào
* Người tạo: Phan Huy Chương
* Ngày tạo: 25/7/2022
* Version 1.0
 */
public final class InputCheck {

	public static boolean isNull(String target) {

		if (target != null && !target.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isInt(String target) {

		if (isNull(target)) {
			return false;
		}
		try {
			Integer.parseInt(target);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isFloat(String target) {

		if (isNull(target)) {
			return false;
		}
		try {
			Float.parseFloat(target);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isDateFormatYYYY(String target) {
		if (isNull(target)) {
			return false;
		}
		if (target.matches("\\d{4}")) {
			return true;
		}
		return false;
	}
	
	public static boolean isDateFormatDDMMYYYY(String target) {
		if (isNull(target)) {
			return false;
		}
		if (target.matches("\\d{2}/\\d{2}/\\d{4}")) {
			return true;
		}
		return false;
	}

}
