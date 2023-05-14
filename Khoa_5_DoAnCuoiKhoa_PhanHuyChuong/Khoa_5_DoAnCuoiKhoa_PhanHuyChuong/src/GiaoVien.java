import java.time.LocalDate;
import java.util.Scanner;

/**
* Mục đích: quản lý các nghiệp vụ liên quan đến giáo viên
* Người tạo: Phan Huy Chương
* Ngày tạo: 25/7/2022
* Version 1.0
 */
public class GiaoVien extends ThanhVien {

	private String namBatDauDay;
	private int chuyenMon;
	private int thamNien;

	public String getNamBatDauDay() {
		return namBatDauDay;
	}

	public void setNamBatDauDay(String namBatDauDay) {
		this.namBatDauDay = namBatDauDay;
	}

	public int getChuyenMon() {
		return chuyenMon;
	}

	public void setChuyenMon(int chuyenMon) {
		this.chuyenMon = chuyenMon;

	}

	public int getThamNien() {
		return thamNien;
	}

	public GiaoVien() {

	}

	public GiaoVien(String hoTen, String namSinh, String noiSinh, String diaChi, String namDatDauDay,
			int chuyenMon) {
		super(hoTen, namSinh, noiSinh, diaChi);
		this.namBatDauDay = namDatDauDay;
		this.chuyenMon = chuyenMon;
	}

	public void tinhSoNamGiangDay() {
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		int namDatDau = Integer.parseInt(namBatDauDay);
		this.thamNien = currentYear - namDatDau;
	}

	@Override
	public void xuat() {
		String chuyenMon = "";
		if (this.chuyenMon == Conts.TU_NHIEN) {
			chuyenMon = "Tu nhien";
		} else if (this.chuyenMon == Conts.XA_HOI) {
			chuyenMon = "Xa hoi";
		}
		super.xuat();
		System.out.println("\t Nam bat dau day : " + this.namBatDauDay + "\t Chuyen mon : " + chuyenMon
				+ "\t Tham nien : " + this.thamNien + " nam");
	}

	public void xuatRowFormat() {
		String text;
		text = PrintFormat.formatTextCell(this.hoTen)+ "|";
		text += PrintFormat.formatTextCell(this.namSinh) + "|";
		text += PrintFormat.formatTextCell(this.noiSinh) + "|";
		text += PrintFormat.formatLongTextCell(this.diaChi) + "|";
		text += PrintFormat.formatTextCell(this.namBatDauDay) + "|";
		String chuyenMon = "";
		if (this.chuyenMon == Conts.TU_NHIEN) {
			chuyenMon = "Tu nhien";
		} else if (this.chuyenMon == Conts.XA_HOI) {
			chuyenMon = "Xa hoi";
		}
		text += PrintFormat.formatTextCell(chuyenMon) + "|";
		text += PrintFormat.formatNumCell(this.thamNien) + "|";
		text +=PrintFormat.formatTextCell("XXXXXXXXXXXXXXXXXXXX||");
		System.out.println(text);

	}
	

	public void nhap(Scanner scan) {
		super.nhap(scan);
		this.namBatDauDay = nhapNamBatDauDay(scan);
		this.chuyenMon = nhapChuyenMon(scan);
	}

	private String nhapNamBatDauDay(Scanner scan) {
		boolean inputErr = true;
		String namBatDau;
		LocalDate localDate = LocalDate.now();
		int currentYear = localDate.getYear();
		do {
			System.out.print("Vui long nhap nam bat dau giang day cua giao vien (YYYY) : ");
			namBatDau = scan.nextLine();
			if (InputCheck.isDateFormatYYYY(namBatDau)) {
				int namBD = Integer.parseInt(namBatDau);
				if (currentYear >= namBD) {
					inputErr = false;
				}
			}

		} while (inputErr);

		return namBatDau;
	}

	private int nhapChuyenMon(Scanner scan) {
		boolean inputErr = true;
		int chuyenMon = 0;
		do {
			System.out.print("Vui long nhap chuyen mon cua giao vien (0 : Tu nhien, 1 : Xa hoi ) : ");
			String nhap = scan.nextLine();
			if (InputCheck.isInt(nhap)) {
				chuyenMon = Integer.parseInt(nhap);
				if (chuyenMon == Conts.TU_NHIEN || chuyenMon == Conts.XA_HOI) {
					inputErr = false;
				}
			}
		} while (inputErr);
		return chuyenMon;
	}

}
