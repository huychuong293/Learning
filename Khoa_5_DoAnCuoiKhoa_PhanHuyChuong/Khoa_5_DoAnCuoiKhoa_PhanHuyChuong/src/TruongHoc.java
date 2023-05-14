import java.util.ArrayList;
import java.util.Collections;


/**
* Người tạo: Phan Huy Chương
* Ngày tạo: 25/7/2022
* Version 1.0
 */
public class TruongHoc implements NhapXuat {

	private DanhSachThanhVien objDSthanhVien;
	public DanhSachThanhVien getObjDSthanhVien() {
		return objDSthanhVien;
	}

	public void setObjDSthanhVien(DanhSachThanhVien objDSthanhVien) {
		this.objDSthanhVien = objDSthanhVien;
	}

	public TruongHoc() {

	}

	@Override
	public void xuat() {

	}

	public void nhap() {

	}

	public boolean themGiaoVien(GiaoVien gv) {
		return this.objDSthanhVien.themGiaoVien(gv);
	}

	public boolean themHocSinh(HocSinh hs) {
		return this.objDSthanhVien.themHocSinh(hs);
	}

	public void xuatDSHocSinh(ArrayList<HocSinh> hocSinh) {
		this.objDSthanhVien.xuatHocSinhTheoFormat(hocSinh);
	}

	public void xuatDSGiaoVien(ArrayList<GiaoVien> giaoVien) {
		this.objDSthanhVien.xuatGiaoVienTheoFormat(giaoVien);
	}

	public void taoDuLieu() throws Exception {
		this.objDSthanhVien = new DanhSachThanhVien();
		this.objDSthanhVien.taoDLHocSinh();
		this.objDSthanhVien.taoDLGiaoVien();
	}

	public ArrayList<HocSinh> layDSHocSinh() {
		ArrayList<HocSinh> listHocSinh = new ArrayList<HocSinh>();
		// Lay ra danh sach giao vien
		for (ThanhVien tv : this.objDSthanhVien.getListThanhVien()) {
			if (tv instanceof HocSinh) {
				listHocSinh.add((HocSinh) tv);
			}
		}
		return listHocSinh;
	}

	public ArrayList<GiaoVien> layDSGiaoVien() {
		ArrayList<GiaoVien> listGiaoVien = new ArrayList<GiaoVien>();
		// Lay ra danh sach giao vien
		for (ThanhVien tv : this.objDSthanhVien.getListThanhVien()) {
			if (tv instanceof GiaoVien) {
				listGiaoVien.add((GiaoVien) tv);
			}
		}
		return listGiaoVien;
	}

	public ArrayList<GiaoVien> timGiaoVienCoSoNamGiangDayCaoNhat() {
		ArrayList<GiaoVien> listGiaoVien = new ArrayList<GiaoVien>();
		listGiaoVien = layDSGiaoVien();
		int maxThamNien = 0;
		int indexMax = 0;
		// Tim so nam giang day cao nhat
		ArrayList<GiaoVien> listGVCoThamNienCaoNhat = new ArrayList<GiaoVien>();
		for (int i = 0; i < listGiaoVien.size(); i++) {
			if (listGiaoVien.get(i).getThamNien() > maxThamNien) {
				maxThamNien = listGiaoVien.get(i).getThamNien();
				indexMax = i;
			}
		}

		// Tim cac giao vien khac co cung so nam giang day la cao nhat
		for (int i = indexMax; i < listGiaoVien.size(); i++) {
			if (listGiaoVien.get(i).getThamNien() == maxThamNien) {
				listGVCoThamNienCaoNhat.add(listGiaoVien.get(i));
			}
		}
		return listGVCoThamNienCaoNhat;
	}

	public ArrayList<GiaoVien> timGiaoVienCoSoNamGiangDayTren5NamThuocKhoituNhien() {
		ArrayList<GiaoVien> listGiaoVien = new ArrayList<GiaoVien>();
		listGiaoVien = layDSGiaoVien();
		ArrayList<GiaoVien> listGVCoSoNamGiangDayTren5NamThuocKhoiTuNhien = new ArrayList<GiaoVien>();
		for (GiaoVien giaoVien : listGiaoVien) {
			if (giaoVien.getThamNien() > 5 && giaoVien.getChuyenMon() == Conts.TU_NHIEN) {
				listGVCoSoNamGiangDayTren5NamThuocKhoiTuNhien.add(giaoVien);
			}
		}
		return listGVCoSoNamGiangDayTren5NamThuocKhoiTuNhien;
	}

	public float timDiemTrungBinhCaoNhat(ArrayList<HocSinh> listHocSinh) {
		float diemTBCaoNhat = 0;
		for (HocSinh hocSinh : listHocSinh) {
			if (hocSinh.getDiemTB() > diemTBCaoNhat) {
				diemTBCaoNhat = hocSinh.getDiemTB();
			}
		}
		return diemTBCaoNhat;
	}

	public ArrayList<HocSinh> timHSCoDiemTrungBinhCaoNhat(ArrayList<HocSinh> listHocSinh) {
		ArrayList<HocSinh> listHSCoDiemTBCaoNhat = new ArrayList<HocSinh>();
		float maxDiemTB = timDiemTrungBinhCaoNhat(listHocSinh);
		for (HocSinh hocSinh : listHocSinh) {
			if (hocSinh.getDiemTB() == maxDiemTB) {
				listHSCoDiemTBCaoNhat.add(hocSinh);
			}
		}
		return listHSCoDiemTBCaoNhat;
	}

	public void sapXepHSTheoDTBGiamDan(ArrayList<HocSinh> listHocSinh, int left, int right) {
		int middle = left + (right - left) / 2;
		float pivot = listHocSinh.get(middle).getDiemTB();
		int i = left, j = right;

		while (i <= j) {

			// Sap xep giam dan

			while (listHocSinh.get(i).getDiemTB() > pivot) {
				i++;
			}

			while (listHocSinh.get(j).getDiemTB() < pivot) {
				j--;
			}

			if (i <= j) {
				Collections.swap(listHocSinh, i, j);
				i++;
				j--;
			}
		}

		if (left < j)
			sapXepHSTheoDTBGiamDan(listHocSinh, left, j);

		if (right > i)
			sapXepHSTheoDTBGiamDan(listHocSinh, i, right);
	}

	@SuppressWarnings("unused")
	private void interchangeSort(ArrayList<HocSinh> _list) {
		int i;
		int j;
		for (i = 0; i < _list.size() - 1; i++) {
			for (j = i + 1; j < _list.size(); j++) {
				HocSinh personI = _list.get(i);
				HocSinh personJ = _list.get(j);
				if (personJ.getHoTen().compareToIgnoreCase(personI.getHoTen()) < 0) {
					Collections.swap(_list, i, j);
				}
			}
		}

	}

	private String getLastName(String fullName) {
		try {
			String name[] = fullName.split(" ");
			String lastName = name[name.length - 1];
			return lastName;

		} catch (Exception e) {
			return fullName;
		}
	}

	private String getMidName(String fullName) {
		try {
			String name[] = fullName.split(" ");
			String midName = name[name.length - 2];
			return midName;

		} catch (Exception e) {
			return fullName;
		}
	}

	private String getFirstName(String fullName) {
		try {
			String name[] = fullName.split(" ");
			String firstName = name[0];
			return firstName;

		} catch (Exception e) {
			return fullName;
		}
	}

	private void bubbleSort(ArrayList<HocSinh> _list) {
		int i, j;
		for (i = 0; i < _list.size() - 1; i++) {
			for (j = _list.size() - 1; j > i; j--) {
				HocSinh person1 = _list.get(j);
				HocSinh person2 = _list.get(j - 1);

				// Liem tra ten rieng
				if (getLastName(person1.getHoTen()).compareToIgnoreCase(getLastName(person2.getHoTen())) < 0) {
					Collections.swap(_list, j - 1, j);
				}
				// Neu 2 nguoi co ten trung nhau, kiem tra ten dem
				if (getLastName(person1.getHoTen()).compareToIgnoreCase(getLastName(person2.getHoTen())) == 0) {
					if (getMidName(person1.getHoTen()).compareToIgnoreCase(getMidName(person2.getHoTen())) < 0) {
						Collections.swap(_list, j - 1, j);
					}
				}
				// Neu trung nhau ca ten dem va ten rieng, ktra ho
				if (getMidName(person1.getHoTen()).compareToIgnoreCase(getMidName(person2.getHoTen())) == 0) {
					if (getFirstName(person1.getHoTen()).compareToIgnoreCase(getFirstName(person2.getHoTen())) < 0) {
						Collections.swap(_list, j - 1, j);
					}
				}
			}
		}
	}

	public void sortName(ArrayList<HocSinh> _list) {
		bubbleSort(_list);
	}

	// Test in du lieu ra file
	public void inDuLieuHocSinh() {
		ArrayList<HocSinh> listHS = layDSHocSinh();
		this.objDSthanhVien.inDuLieuHocSinh(listHS);
	}

	public void inDuLieuGiaoVien() {
		ArrayList<GiaoVien> listGV = layDSGiaoVien();
		this.objDSthanhVien.inDuLieuGiaoVien(listGV);
	}
}
