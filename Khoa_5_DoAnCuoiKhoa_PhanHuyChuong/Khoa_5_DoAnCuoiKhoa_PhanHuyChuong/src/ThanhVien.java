import java.time.LocalDate;
import java.util.Scanner;

/**
* Mục đích: quản lý các thành viên trong trường ở mức cha
*Người tạo: Phan Huy Chương
* Ngày tạo: 25/7/2022
* Version 1.0
 */
public abstract class ThanhVien implements NhapXuat {

	protected String hoTen;
	protected String namSinh;
	protected String noiSinh;
	protected String diaChi;

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(String namSinh) {
		this.namSinh = namSinh;
	}

	public String getNoiSinh() {
		return noiSinh;
	}

	public void setNoiSinh(String noiSinh) {
		this.noiSinh = noiSinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public ThanhVien() {

	}

	public ThanhVien(String hoTen, String namSinh, String noiSinh, String diaChi) {
		this.hoTen = hoTen;
		this.namSinh = namSinh;
		this.noiSinh = noiSinh;
		this.diaChi = diaChi;
	}

	public void nhap(Scanner scan) {
		this.hoTen = VNCharacterUtils.removeAccent(nhapHoTen(scan));
		this.namSinh = nhapNamSinh(scan);
		this.noiSinh = VNCharacterUtils.removeAccent(nhapNoiSinh(scan));
		this.diaChi = VNCharacterUtils.removeAccent(nhapDiaChi(scan));

	}

	private String nhapHoTen(Scanner scan) {
		boolean inputErr = true;
		String ten;
		do {
			System.out.print("Vui long nhap ho ten : ");
			ten = scan.nextLine();
			if (!InputCheck.isNull(ten)) {
				inputErr = false;
			}
		} while (inputErr);
		return ten;
	}

	private String nhapNamSinh(Scanner scan) {
		boolean inputErr = true;
		String namSinh;
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		
		do {
			System.out.print("Vui long nhap nam sinh (YYYY) : ");
			namSinh = scan.nextLine();
			if (InputCheck.isDateFormatYYYY(namSinh)) {
				int nam = Integer.parseInt(namSinh);
				if ((currentYear -100 < nam) && nam < currentYear) {
					inputErr = false;
				}
			}
		} while (inputErr);
		return namSinh;
	}

	private String nhapNoiSinh(Scanner scan) {
		boolean inputErr = true;
		String noiSinh;
		do {
			System.out.print("Vui long nhap noi sinh : ");
			noiSinh = scan.nextLine();
			if (!InputCheck.isNull(noiSinh)) {
				inputErr = false;
			}
		} while (inputErr);
		return noiSinh;
	}

	private String nhapDiaChi(Scanner scan) {
		boolean inputErr = true;
		String diaChi;
		do {
			System.out.print("Vui long nhap dia chi : ");
			diaChi = scan.nextLine();
			if (!InputCheck.isNull(diaChi)) {
				inputErr = false;
			}
		} while (inputErr);
		return diaChi;
	}

	public void xuat() {
		System.out.print("Ho Ten : " + this.hoTen
				+ "\t Nam Sinh : " + this.namSinh
				+ "\t Noi Sinh : " + this.noiSinh
				+ "\t Dia Chi : " + this.diaChi);
	}

}
