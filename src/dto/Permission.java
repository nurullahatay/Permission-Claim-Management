package dto;

public class Permission {
	private long id;
	private long sicilNo; // PersonelbilgisiDto'sunda var.
	private int formNo;
	private String formTarihi;
	private String baslangicTarihi;
	private String bitisTarihi;
	private int gun;
	private String izinNedeni;
	private String aciklama;
	private String telefonNumarasi;
	private String adres;
	private String birinciYoneticiOnayi;
	private String ikinciYoneticiOnayi;
	private String ikOnayi;
	private String durum;
	private long formFiller;
	private String comment;
	
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
	
	
	public String getBirinciYoneticiOnayi() {
		return birinciYoneticiOnayi;
	}
	public void setBirinciYoneticiOnayi(String birinciYoneticiOnayi) {
		this.birinciYoneticiOnayi = birinciYoneticiOnayi;
	}
	public String getIkinciYoneticiOnayi() {
		return ikinciYoneticiOnayi;
	}
	public void setIkinciYoneticiOnayi(String ikinciYoneticiOnayi) {
		this.ikinciYoneticiOnayi = ikinciYoneticiOnayi;
	}
	public String getIkOnayi() {
		return ikOnayi;
	}
	public void setIkOnayi(String ikOnayi) {
		this.ikOnayi = ikOnayi;
	}
	public String getDurum() {
		return durum;
	}
	public void setDurum(String durum) {
		this.durum = durum;
	}
	public long getFormFiller() {
		return formFiller;
	}
	public void setFormFiller(long formFiller) {
		this.formFiller = formFiller;
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", sicilNo=" + sicilNo + ", formNo=" + formNo + ", formTarihi=" + formTarihi
				+ ", baslangicTarihi=" + baslangicTarihi + ", bitisTarihi=" + bitisTarihi + ", gun=" + gun
				+ ", izinNedeni=" + izinNedeni + ", aciklama=" + aciklama + ", telefonNumarasi=" + telefonNumarasi
				+ ", adres=" + adres + ", birinciYoneticiOnayi=" + birinciYoneticiOnayi + ", ikinciYoneticiOnayi="
				+ ikinciYoneticiOnayi + ", ikOnayi=" + ikOnayi + ", durum=" + durum + ", formFiller=" + formFiller
				+ "]";
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	
}
