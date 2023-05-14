import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

/** 
* mục đích: quản lý các thành viên trong trường
* Người tạo: Phan Huy Chương
* Ngày tạo: 25/7/2022
* Version 1.0
 */
public class DanhSachThanhVien implements NhapXuat {

	private ArrayList<ThanhVien> listThanhVien;

	public ArrayList<ThanhVien> getListThanhVien() {
		return listThanhVien;
	}

	public void setListThanhVien(ArrayList<ThanhVien> listThanhVien) {
		this.listThanhVien = listThanhVien;
	}

	public DanhSachThanhVien() {
		this.listThanhVien = new ArrayList<ThanhVien>();
	}

	@Override
	public void xuat() {

	}

	public void nhap() {

	}

	public boolean themHocSinh(HocSinh hs) {
		try {
			this.listThanhVien.add(hs);
			hs.tinhDiemTB();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean themGiaoVien(GiaoVien gv) {
		try {
			this.listThanhVien.add(gv);
			gv.tinhSoNamGiangDay();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void xuatLine() {
		System.out.println(
				"==============================================================================================================================================================================================================================");
	}

	private String formatCell(String paddLeft, String title, String paddRight) {
		return String.format(paddLeft, " ") + title + String.format(paddRight, " ");
	}

	private void xuatRowHeaderHocSinh() {
		String paddString1 = "%3s";
		String paddString2 = "%4s";
		String paddString3 = "%6s";
		String paddString4 = "%25s";
		String paddString5 = "%15s";
		String paddString6 = "%10s";
		xuatLine();
		String text;
		text = "||" + formatCell(paddString1, "STT", paddString1) + "|";
		text += formatCell(paddString2, "Ho Ten", paddString5) + "|";
		text += formatCell(paddString3, "Nam Sinh", paddString6) + "|";
		text += formatCell(paddString3, "Noi sinh", paddString6) + "|";
		text += formatCell(paddString6, "Dia chi", paddString4) + "|";
		text += formatCell(paddString2, "Diem Van", paddString1) + "|";
		text += formatCell(paddString2, "Diem Toan", paddString1) + "|";
		text += formatCell(paddString1, "Diem NN", paddString1) + "|";
		text += formatCell(paddString2, "Diem TB", paddString1) + "|";
		text += formatCell(paddString2, "Xep Loai", paddString5) + "||";
		System.out.println(text);
		xuatLine();
	}

	private void xuatRowHeaderGiaoVien() {
		String paddString1 = "%3s";
		String paddString2 = "%4s";
		String paddString3 = "%6s";
		String paddString4 = "%25s";
		String paddString5 = "%15s";
		String paddString6 = "%10s";
		xuatLine();
		String text;
		text = "||" + formatCell(paddString1, "STT", paddString1) + "|";
		text += formatCell(paddString2, "Ho Ten", paddString5) + "|";
		text += formatCell(paddString3, "Nam Sinh", paddString6) + "|";
		text += formatCell(paddString3, "Noi sinh", paddString6) + "|";
		text += formatCell(paddString6, "Dia chi", paddString4) + "|";
		text += formatCell(paddString2, "Nam Bat Dau", paddString6) + "|";
		text += formatCell(paddString2, "Chuyen Mon", paddString6) + "|";
		text += formatCell("", "Tham nien (nam)", "") + "|";
		text += formatCell(paddString1, "XXXXXXXXXXXXXXXX", paddString1) + "||";
		System.out.println(text);
		xuatLine();
	}

	private void xuatCellThuTu(int i) {
		String paddLeft = "%3s";
		String paddRight = "%-6s";
		String text = "||" + String.format(paddLeft, " ") + String.format(paddRight, "" + i) + "|";
		System.out.print(text);
	}

	public void xuatHocSinhTheoFormat(ArrayList<HocSinh> list) {
		xuatRowHeaderHocSinh();
		int i = 1;
		for (HocSinh nv : list) {
			xuatCellThuTu(i);
			nv.xuatRowFormat();
			i++;
		}
		xuatLine();
		System.out.println("");
	}

	public void xuatGiaoVienTheoFormat(ArrayList<GiaoVien> list) {
		xuatRowHeaderGiaoVien();
		int i = 1;
		for (GiaoVien nv : list) {
			xuatCellThuTu(i);
			nv.xuatRowFormat();
			i++;
		}
		xuatLine();
		System.out.println("");
	}

	@SuppressWarnings("resource")
	public void taoDLHocSinh() throws Exception {
		try {
			FileReader reader = new FileReader("src/DSHocSinh.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			int countLine = 0;
			while ((line = bufferedReader.readLine()) != null) {
				countLine++;
				String[] listInfo = line.split(" # ");
				
				//  kiem tra so luong item
				if (listInfo.length != 7) {
					throw new Exception("Dong " + countLine + " so luong item khong dung");
				}

				// Kiem tra du lieu nam sinh
				String namSinh = listInfo[1];
				if (!InputCheck.isDateFormatYYYY(namSinh)) {
					throw new Exception(
							"Dong " + countLine + " dinh dang du lieu khong dung (item = Nam sinh)");
				}

				// Kiem tra du lieu diem van
				if (!InputCheck.isFloat(listInfo[4])) {
					throw new Exception(
							"Dong " + countLine + " dinh dang du lieu khong dung (item = Diem Van)");
				}
				float diemVan = Float.parseFloat(listInfo[4]);
				if (diemVan < Conts.DIEM_TOI_THIEU || diemVan > Conts.DIEM_TOI_DA) {
					throw new Exception(
							"Dong " + countLine + " dinh dang du lieu khong dung (item = Diem Van)");
				}

				// Kiem tra du lieu diem toan
				if (!InputCheck.isFloat(listInfo[5])) {
					throw new Exception(
							"Dong " + countLine + " dinh dang du lieu khong dung (item = Diem Toan)");
				}
				float diemToan = Float.parseFloat(listInfo[5]);
				if (diemToan < Conts.DIEM_TOI_THIEU || diemToan > Conts.DIEM_TOI_DA) {
					throw new Exception(
							"Dong " + countLine + " dinh dang du lieu khong dung (item = Diem Toan)");
				}

				// Kiem tra du lieu diem ngoai ngu
				if (!InputCheck.isFloat(listInfo[6])) {
					throw new Exception(
							"Dong " + countLine + " dinh dang du lieu khong dung (item = Diem Ngoai Ngu)");
				}
				float diemNN = Float.parseFloat(listInfo[6]);
				if (diemNN < Conts.DIEM_TOI_THIEU || diemNN > Conts.DIEM_TOI_DA) {
					throw new Exception(
							"Dong " + countLine + " dinh dang du lieu khong dung (item = Diem Ngoai Ngu)");
				}

				// Khoi tao hoc sinh moi
				ThanhVien thanhVien = new HocSinh(VNCharacterUtils.removeAccent(listInfo[0]), namSinh,
						VNCharacterUtils.removeAccent(listInfo[2]), VNCharacterUtils.removeAccent(listInfo[3]),
						diemVan, diemToan, diemNN);

				// them hoc sinh vao list 
				this.listThanhVien.add(thanhVien);

				// Tinh diem tung binh cua hoc sinh
				((HocSinh) thanhVien).tinhDiemTB();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public void taoDLGiaoVien() throws Exception {
		try {
			FileReader reader = new FileReader("src/DSGiaoVien.txt");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String line;
			int countLine = 0;
			while ((line = bufferedReader.readLine()) != null) {
				countLine++;
				String[] listInfo = line.split(" # ");

				if (listInfo.length != 6) {
					throw new Exception("Dong " + countLine + " so luong item khong dung");
				}

				// Kiem tra du lieu nam sinh
				String namSinh = listInfo[1];
				if (!InputCheck.isDateFormatYYYY(namSinh)) {
					throw new Exception(
							"Dong " + countLine + " dinh dang du lieu khong dung (item = Nam sinh)");
				}

				// Kiem tra du lieu nam bat dau day
				String namBatDau = listInfo[4];
				if (!InputCheck.isDateFormatYYYY(namBatDau)) {
					throw new Exception(
							"Dong " + countLine + " dinh dang du lieu khong dung (item = Nam bat dau day)");
				}

				// Kiem tra du lieu chuyen mon
				int chuyenMon = Integer.parseInt(listInfo[5]);
				if (chuyenMon != Conts.TU_NHIEN && chuyenMon != Conts.XA_HOI) {
					throw new Exception("Dong " + countLine + " dinh dang du lieu khong dung (item = Chuyen mon)");
				}

				// Khoi tao lop giao vien
				ThanhVien thanhVien = new GiaoVien(VNCharacterUtils.removeAccent(listInfo[0]), namSinh,
						VNCharacterUtils.removeAccent(listInfo[2]), VNCharacterUtils.removeAccent(listInfo[3]),
						namBatDau,
						chuyenMon);

				// Them giao vien
				this.listThanhVien.add(thanhVien);

				// Tinh so nam giang day
				((GiaoVien) thanhVien).tinhSoNamGiangDay();
			}
			reader.close(); // Dong file
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void inDuLieuHocSinh(ArrayList<HocSinh> listHocSinh) {
		String specialText = " # ";
		try {

			File file = new File("src/DSHocSinh.txt");
			FileOutputStream fileOutStream = new FileOutputStream(file);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOutStream));
			
			// Neu chua co file, tao moi
			if (!file.exists()) {
				file.createNewFile();
			}
			for (HocSinh hocSinh : listHocSinh) {
				String data = "";
				data += hocSinh.getHoTen();
				data += specialText;
				data += hocSinh.getNamSinh();
				data += specialText;
				data += hocSinh.getNoiSinh();
				data += specialText;
				data += hocSinh.getDiaChi();
				data += specialText;
				data += hocSinh.getDiemVan();
				data += specialText;
				data += hocSinh.getDiemToan();
				data += specialText;
				data += hocSinh.getDiemNgoaiNgu();
				bw.write(data);
				bw.newLine();
			}
			bw.close();
			System.out.println("Da luu du lieu hoc sinh");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inDuLieuGiaoVien(ArrayList<GiaoVien> listGiaoVien) {
		String specialText = " # ";
		try {

			File file = new File("src/DSGiaoVien.txt");
			FileOutputStream fileOutStream = new FileOutputStream(file);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOutStream));
			
			// Neu chua co file, tao moi
			if (!file.exists()) {
				file.createNewFile();
			}
			for (GiaoVien giaoVien : listGiaoVien) {
				String data = "";
				data += giaoVien.getHoTen();
				data += specialText;
				data += giaoVien.getNamSinh();
				data += specialText;
				data += giaoVien.getNoiSinh();
				data += specialText;
				data += giaoVien.getDiaChi();
				data += specialText;
				data += giaoVien.getNamBatDauDay();
				data += specialText;
				data += giaoVien.getChuyenMon();
				bw.write(data);
				bw.newLine();
			}
			System.out.println("Da luu du lieu giao vien");
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
