import java.util.ArrayList;
import java.util.Scanner;

/** 
* Mục đích: xử lý nghiệp vụ liên quan đến trường học 
* Người tạo: Phan Huy Chương
* Ngày tạo: 25/7/2022
* Version 1.0
 */
public class Main {

	public static void main(String[] args) {
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println(
				"--------------------------------------------- " + Conts.TEN_TRUONG
						+ " --------------------------------------");
		System.out.println(
				"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("");
		Scanner scan = new Scanner(System.in);
		TruongHoc truongHoc = new TruongHoc();
		try {
			truongHoc.taoDuLieu();
			doMenu(scan, truongHoc);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	private static void inMenu() {
		System.out.println("Nhap 1 : Nhap thong tin giao vien");
		System.out.println("Nhap 2 : Nhap thong tin hoc sinh");
		System.out.println("Nhap 3 : Hien thi danh sach giao vien");
		System.out.println("Nhap 4 : Hien thi danh sach hoc sinh");
		System.out.println(
				"Nhap 5 : Hien thi danh sach giao vien co tham nien lon nhat");
		System.out.println(
				"Nhap 6 : Hien thi danh sach giao vien co tham nien > 5 nam va chuyen mon thuoc khoi tu nhien");
		System.out.println("Nhap 7 : Hien thi danh sach hoc sinh co diem trung binh cao nhat");
		System.out.println("Nhap 8 : Hien thi danh sach hoc sinh theo diem trung binh giam dan");
		System.out.println("Nhap 9 : Hien thi danh sach hoc sinh theo thu tu ABC");
		System.out.println("Nhap 0 : Thoat chuong trinh");

	}

	private static void doMenu(Scanner scan, TruongHoc truongHoc) throws Exception {
		ThanhVien thanhVien;
		boolean flag = true;
		do {
			inMenu();
			System.out.print("Moi chon >>> ");
			int chon = Integer.parseInt(scan.nextLine());
			switch (chon) {
			case 1:
				System.out.println(
						"--------------------------------------------- THEM THONG TIN GIAO VIEN --------------------------------------");
				thanhVien = new GiaoVien();
				thanhVien.nhap(scan);
				boolean themGV = truongHoc.themGiaoVien((GiaoVien) thanhVien);
				System.out.println("。。。");
				if (themGV) {
					System.out.println("Da them thong tin giao vien");
					truongHoc.inDuLieuGiaoVien();
				} else {
					System.out.println("Them thong tin giao vien that bai");
				}
				System.out.println("");
				break;
			case 2:
				System.out.println(
						"--------------------------------------------- THEM THONG TIN HOC SINH --------------------------------------");
				thanhVien = new HocSinh();
				thanhVien.nhap(scan);
				boolean themHS = truongHoc.themHocSinh((HocSinh) thanhVien);
				System.out.println("。。。");
				if (themHS) {
					System.out.println("Da them thong tin hoc sinh");
					truongHoc.inDuLieuHocSinh();
				} else {
					System.out.println("Them thong tin hoc sinh that bai");
				}
				System.out.println("");
				break;
			case 3:
				System.out.println(
						"--------------------------------------------- DANH SACH THONG TIN GIAO VIEN --------------------------------------");
				ArrayList<GiaoVien> listGV = truongHoc.layDSGiaoVien();
				if (listGV.size() > 0) {
					truongHoc.xuatDSGiaoVien(listGV);
				} else {
					System.err.println("Khong co giao vien nao trong danh sach");
				}

				break;
			case 4:
				System.out.println(
						"--------------------------------------------- DANH SACH THONG TIN HOC SINH --------------------------------------");
				ArrayList<HocSinh> listHS = truongHoc.layDSHocSinh();
				if (listHS.size() > 0) {
					truongHoc.xuatDSHocSinh(listHS);
				} else {
					System.err.println("Khong co hoc sinh nao trong danh sach");
				}

				break;
			case 5:
				System.out.println(
						"-------------------------------- DANH SACH THONG TIN GIAO VIEN CO SO NAM GIANG DAY CAO NHAT ------------------------------");
				ArrayList<GiaoVien> listGVCoThamNienCaoNhat = truongHoc.timGiaoVienCoSoNamGiangDayCaoNhat();
				truongHoc.xuatDSGiaoVien(listGVCoThamNienCaoNhat);
				break;
			case 6:
				System.out.println(
						"-------------------------------- DANH SACH THONG TIN GIAO VIEN CO SO NAM GIANG DAY TREN 5 NAM THUOC KHOI TU NHIEN ------------------------------");
				ArrayList<GiaoVien> listGVCoThamNien5NamThuocKhoiTuNhien = truongHoc
						.timGiaoVienCoSoNamGiangDayTren5NamThuocKhoituNhien();
				truongHoc.xuatDSGiaoVien(listGVCoThamNien5NamThuocKhoiTuNhien);
				break;
			case 7:
				System.out.println(
						"-------------------------------- DANH SACH THONG TIN HOC SINH CO DIEM TRUNG BINH CAO NHAT ------------------------------");
				ArrayList<HocSinh> listHS1 = truongHoc.layDSHocSinh();
				if (listHS1.size() > 0) {
					float diemTBCaoNhat = truongHoc.timDiemTrungBinhCaoNhat(listHS1);
					ArrayList<HocSinh> listHSCoDiemTBCaoNhat = truongHoc.timHSCoDiemTrungBinhCaoNhat(listHS1);
					System.out.println("Diem trung binh cao nhat la : " + diemTBCaoNhat);
					truongHoc.xuatDSHocSinh(listHSCoDiemTBCaoNhat);
				}
				break;
			case 8:
				System.out.println(
						"-------------------------------- DANH SACH THONG TIN HOC SINH CO DIEM TRUNG BINH GIAM DAN ------------------------------");
				ArrayList<HocSinh> listHS2 = truongHoc.layDSHocSinh();
				if (listHS2.size() > 0) {
					truongHoc.sapXepHSTheoDTBGiamDan(listHS2, 0, listHS2.size() - 1);
					truongHoc.xuatDSHocSinh(listHS2);
				}
				break;
			case 9:
				System.out.println(
						"-------------------------------- DANH SACH THONG TIN HOC SINH SAP XEP THU TU ABC ------------------------------");
				ArrayList<HocSinh> listHS3 = truongHoc.layDSHocSinh();
				if (listHS3.size() > 0) {

					truongHoc.sortName(listHS3);
					truongHoc.xuatDSHocSinh(listHS3);
				}
				break;
			case 0:
				flag = false;
				System.out.println(
						"----------------------------------- DA THOAT -----------------------------------");
				break;

			default:
				System.out.println("Vui long chon dung menu");
			}
		} while (flag);
	}

}
