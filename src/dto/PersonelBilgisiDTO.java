package dto;

import java.util.Date;

public class PersonelBilgisiDTO {
	private long sicilno;
	private String ad;
	private String soyad;
	private int telefonnumarasi;
	private String adres;
	private String isebaslangictarihi;
	private String departman;
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

	public int getTelefonnumarasi() {
		return telefonnumarasi;
	}

	public void setTelefonnumarasi(int telefonnumarasi) {
		this.telefonnumarasi = telefonnumarasi;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getIsebaslangictarihi() {
		return isebaslangictarihi;
	}

	public void setIsebaslangictarihi(String isebaslangictarihi) {
		this.isebaslangictarihi = isebaslangictarihi;
	}

	public String getDepartman() {
		return departman;
	}

	public void setDepartman(String departman) {
		this.departman = departman;
	}

	public boolean isIkinciyoneticionay() {
		return ikinciyoneticionay;
	}

	public void setIkinciyoneticionay(boolean ikinciyoneticionay) {
		this.ikinciyoneticionay = ikinciyoneticionay;
	}

}
