import java.util.Scanner;

/**
* Mục đích: quản lý các nghiệp vụ liên quan đến học sinh
* Người tạo: Phan Huy Chương
* Ngày tạo: 25/7/2022
* Version 1.0
 */
public class HocSinh extends ThanhVien {

	private float diemVan;
	private float diemToan;
	private float diemNgoaiNgu;
	private float diemTB;
	private String xepLoai;

	public float getDiemVan() {
		return diemVan;
	}

	public void setDiemVan(float diemVan) {
		this.diemVan = diemVan;
	}

	public float getDiemToan() {
		return diemToan;
	}

	public void setDiemToan(float diemToan) {
		this.diemToan = diemToan;
	}

	public float getDiemNgoaiNgu() {
		return diemNgoaiNgu;
	}

	public void setDiemNgoaiNgu(float diemNgoaiNgu) {
		this.diemNgoaiNgu = diemNgoaiNgu;
	}

	public float getDiemTB() {
		return diemTB;
	}

	public String getXepLoai() {
		return xepLoai;
	}

	public void setXepLoai(String xepLoai) {
		this.xepLoai = xepLoai;
	}

	public HocSinh() {

	}

	public HocSinh(String hoTen, String namSinh, String noiSinh, String diaChi, float diemVan, float diemToan,
			float diemNgoaiNgu) {
		super(hoTen, namSinh, noiSinh, diaChi);
		this.diemVan = diemVan;
		this.diemToan = diemToan;
		this.diemNgoaiNgu = diemNgoaiNgu;
	}

	public void tinhDiemTB() {
		float diemTB = (this.diemVan + this.diemToan + this.diemNgoaiNgu) / 3;
		this.diemTB = (float) (Math.round(diemTB * 100.0) / 100.0); // Lam tron den 2 chu so sau dau phay
		this.xepLoaiHocLuc();
	}

	public void xepLoaiHocLuc() {
		if (this.diemTB >= 9 && this.diemTB < Conts.DIEM_TOI_DA) {
			this.xepLoai = "Xuat sac";
		} else if (this.diemTB >= 8 && this.diemTB < 9) {
			this.xepLoai = "Gioi";
		} else if (this.diemTB >= 7 && this.diemTB < 8) {
			this.xepLoai = "Kha";
		} else if (this.diemTB >= 5 && this.diemTB < 7) {
			this.xepLoai = "Trung binh";
		} else if (this.diemTB >= Conts.DIEM_TOI_THIEU && this.diemTB < 5) {
			this.xepLoai = "Yeu";
		}
	}

	@Override
	public void xuat() {
		super.xuat();
		System.out.println("\t Diem Toan : " + this.diemToan
				+ "\t Diem Van : " + this.diemVan
				+ "\t Diem Ngoai Ngu : " + this.diemNgoaiNgu
				+ "\t Diem Trung Binh : " + this.diemTB
				+ "\t Xep Loai : " + this.xepLoai);
	}

	public void xuatRowFormat() {
		String text;
		text = PrintFormat.formatTextCell(this.hoTen) + "|";
		text += PrintFormat.formatTextCell(this.namSinh) + "|";
		text += PrintFormat.formatTextCell(this.noiSinh) + "|";
		text += PrintFormat.formatLongTextCell(this.diaChi) + "|";
		text += PrintFormat.formatNumCell(this.diemVan) + "|";
		text += PrintFormat.formatNumCell(this.diemToan) + "|";
		text += PrintFormat.formatNumCell(this.diemNgoaiNgu) + "|";
		text += PrintFormat.formatNumCell(this.diemTB) + "|";
		text += PrintFormat.formatTextCell(this.xepLoai) + "||";
		System.out.println(text);

	}

	@Override
	public void nhap(Scanner scan) {
		super.nhap(scan);
		this.diemVan = nhapDiemVan(scan);
		this.diemToan = nhapDiemToan(scan);
		this.diemNgoaiNgu = nhapDiemNgoaiNgu(scan);

	}

	private float nhapDiemVan(Scanner scan) {
		boolean inputErr = true;
		float diemVan = 0;
		do {
			System.out.print("Vui long nhap diem mon Van : ");
			String nhap = scan.nextLine();
			if (InputCheck.isFloat(nhap)) {
				diemVan = Float.parseFloat(nhap);
				if (diemVan >= Conts.DIEM_TOI_THIEU && diemVan <= Conts.DIEM_TOI_DA) {
					inputErr = false;
				}
			}
		} while (inputErr);
		return diemVan;
	}

	private float nhapDiemToan(Scanner scan) {
		boolean inputErr = true;
		float diemToan = 0;
		do {
			System.out.print("Vui long nhap diem mon Toan : ");
			String nhap = scan.nextLine();
			if (InputCheck.isFloat(nhap)) {
				diemToan = Float.parseFloat(nhap);
				if (diemToan >= Conts.DIEM_TOI_THIEU && diemToan <= Conts.DIEM_TOI_DA) {
					inputErr = false;
				}
			}
		} while (inputErr);
		return diemToan;
	}

	private float nhapDiemNgoaiNgu(Scanner scan) {
		boolean inputErr = true;
		float diemNN = 0;
		do {
			System.out.print("Vui long nhap diem mon Ngoai Ngu: ");
			String nhap = scan.nextLine();
			if (InputCheck.isFloat(nhap)) {
				diemNN = Float.parseFloat(nhap);
				if (diemNN >= Conts.DIEM_TOI_THIEU && diemNN <= Conts.DIEM_TOI_DA) {
					inputErr = false;
				}
			}
		} while (inputErr);
		return diemNN;
	}

}
