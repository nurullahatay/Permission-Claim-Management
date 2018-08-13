package dto;

import java.util.Date;

public class IzinTalebiDTO {
	private long id;
	private long sicilno; // PersonelbilgisiDto'sunda var.
	private Date izinolusturmatarihi;
	private Date baslangictarihi;
	private Date donustarihi;
	private int gun;
	private String izinnedeni;
	private String aciklama;
	private boolean birinciyoneticionayi;
	private boolean ikinciyoneticionayi;
	private boolean ikonayi;
	private boolean durum;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSicilno() {
		return sicilno;
	}

	public void setSicilno(long sicilno) {
		this.sicilno = sicilno;
	}

	public Date getIzinolusturmatarihi() {
		return izinolusturmatarihi;
	}

	public void setIzinolusturmatarihi(Date izinolusturmatarihi) {
		this.izinolusturmatarihi = izinolusturmatarihi;
	}

	public Date getBaslangictarihi() {
		return baslangictarihi;
	}

	public void setBaslangictarihi(Date baslangictarihi) {
		this.baslangictarihi = baslangictarihi;
	}

	public Date getDonustarihi() {
		return donustarihi;
	}

	public void setDonustarihi(Date donustarihi) {
		this.donustarihi = donustarihi;
	}

	public int getGun() {
		return gun;
	}

	public void setGun(int gun) {
		this.gun = gun;
	}

	public String getIzinnedeni() {
		return izinnedeni;
	}

	public void setIzinnedeni(String izinnedeni) {
		this.izinnedeni = izinnedeni;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public boolean isBirinciyoneticionayi() {
		return birinciyoneticionayi;
	}

	public void setBirinciyoneticionayi(boolean birinciyoneticionayi) {
		this.birinciyoneticionayi = birinciyoneticionayi;
	}

	public boolean isIkinciyoneticionayi() {
		return ikinciyoneticionayi;
	}

	public void setIkinciyoneticionayi(boolean ikinciyoneticionayi) {
		this.ikinciyoneticionayi = ikinciyoneticionayi;
	}

	public boolean isIkonayi() {
		return ikonayi;
	}

	public void setIkonayi(boolean ikonayi) {
		this.ikonayi = ikonayi;
	}

	public boolean isDurum() {
		return durum;
	}

	public void setDurum(boolean durum) {
		this.durum = durum;
	}

}
