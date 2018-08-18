package dto;

public class IzinTalebiDTO {
	private long id;
	private long sicilno; // PersonelbilgisiDto'sunda var.
	private String izinolusturmatarihi;
	private String baslangictarihi;
	private String donustarihi;
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

	public String getIzinolusturmatarihi() {
		return izinolusturmatarihi;
	}

	public void setIzinolusturmatarihi(String izinolusturmatarihi) {
		this.izinolusturmatarihi = izinolusturmatarihi;
	}

	public String getBaslangictarihi() {
		return baslangictarihi;
	}

	public void setBaslangictarihi(String baslangictarihi) {
		this.baslangictarihi = baslangictarihi;
	}

	public String getDonustarihi() {
		return donustarihi;
	}

	public void setDonustarihi(String donustarihi) {
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
