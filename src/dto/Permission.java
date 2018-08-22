package dto;

public class Permission {
	private long id;
	private long sicilNo; // PersonelbilgisiDto'sunda var.
	private String sahibi;
	private int formNo;
	private String formTarihi;
	private String baslangicTarihi;
	private String bitisTarihi;
	private int gun;
	private String izinNedeni;
	private String aciklama;
	private String telefonNumarasi;
	private String adres;
	private boolean birinciYoneticiOnayi;
	private boolean ikinciYoneticiOnayi;
	private boolean ikOnayi;
	private boolean durum;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSicilNo() {
		return sicilNo;
	}
	public void setSicilNo(long sicilNo) {
		this.sicilNo = sicilNo;
	}
	public String getSahibi() {
		return sahibi;
	}
	public void setSahibi(String sahibi) {
		this.sahibi = sahibi;
	}
	public int getFormNo() {
		return formNo;
	}
	public void setFormNo(int formNo) {
		this.formNo = formNo;
	}
	public String getFormTarihi() {
		return formTarihi;
	}
	public void setFormTarihi(String formTarihi) {
		this.formTarihi = formTarihi;
	}
	public String getBaslangicTarihi() {
		return baslangicTarihi;
	}
	public void setBaslangicTarihi(String baslangicTarihi) {
		this.baslangicTarihi = baslangicTarihi;
	}
	public String getBitisTarihi() {
		return bitisTarihi;
	}
	public void setBitisTarihi(String bitisTarihi) {
		this.bitisTarihi = bitisTarihi;
	}
	public int getGun() {
		return gun;
	}
	public void setGun(int gun) {
		this.gun = gun;
	}
	public String getIzinNedeni() {
		return izinNedeni;
	}
	public void setIzinNedeni(String izinNedeni) {
		this.izinNedeni = izinNedeni;
	}
	public String getAciklama() {
		return aciklama;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	public String getTelefonNumarasi() {
		return telefonNumarasi;
	}
	public void setTelefonNumarasi(String telefonNumarasi) {
		this.telefonNumarasi = telefonNumarasi;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public boolean isBirinciYoneticiOnayi() {
		return birinciYoneticiOnayi;
	}
	public void setBirinciYoneticiOnayi(boolean birinciYoneticiOnayi) {
		this.birinciYoneticiOnayi = birinciYoneticiOnayi;
	}
	public boolean isIkinciYoneticiOnayi() {
		return ikinciYoneticiOnayi;
	}
	public void setIkinciYoneticiOnayi(boolean ikinciYoneticiOnayi) {
		this.ikinciYoneticiOnayi = ikinciYoneticiOnayi;
	}
	public boolean isIkOnayi() {
		return ikOnayi;
	}
	public void setIkOnayi(boolean ikOnayi) {
		this.ikOnayi = ikOnayi;
	}
	public boolean isDurum() {
		return durum;
	}
	public void setDurum(boolean durum) {
		this.durum = durum;
	}

	
}
