package dto;

import java.util.ArrayList;

public class Personel {
	private long sicilno;
	private String ad;
	private String soyad;
	private String email;
	private String password;
	private String isebaslangictarihi;
	private Department departman;
	private String pozisyon;
	private ArrayList<String> personelRoles;
	private boolean ikinciyoneticionay;

	public long getSicilno() {
		return sicilno;
	}

	public void setSicilno(long sicilno) {
		this.sicilno = sicilno;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getIsebaslangictarihi() {
		return isebaslangictarihi;
	}

	public void setIsebaslangictarihi(String isebaslangictarihi) {
		this.isebaslangictarihi = isebaslangictarihi;
	}

	public Department getDepartman() {
		return departman;
	}

	public void setDepartman(Department departman) {
		this.departman = departman;
	}

	public boolean isIkinciyoneticionay() {
		return ikinciyoneticionay;
	}

	public void setIkinciyoneticionay(boolean ikinciyoneticionay) {
		this.ikinciyoneticionay = ikinciyoneticionay;
	}

	public String getPozisyon() {
		return pozisyon;
	}

	public void setPozisyon(String pozisyon) {
		this.pozisyon = pozisyon;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<String> getPersonelRoles() {
		return personelRoles;
	}

	public void setPersonelRoles(ArrayList<String> personelRoles) {
		this.personelRoles = personelRoles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
