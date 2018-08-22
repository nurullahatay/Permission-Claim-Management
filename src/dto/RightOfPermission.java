package dto;

import java.util.Date;

public class RightOfPermission {
	private long sicilNo; // PersonelbilgisiDto'sunda var.
	private Date gecerliOlacagiTarih;
	private int hakedilenGunSayisi;
	private int mevcutYilIciHakedilenGunSayisi;
	public long getSicilNo() {
		return sicilNo;
	}
	public void setSicilNo(long sicilNo) {
		this.sicilNo = sicilNo;
	}
	public Date getGecerliOlacagiTarih() {
		return gecerliOlacagiTarih;
	}
	public void setGecerliOlacagiTarih(Date gecerliOlacagiTarih) {
		this.gecerliOlacagiTarih = gecerliOlacagiTarih;
	}
	public int getHakedilenGunSayisi() {
		return hakedilenGunSayisi;
	}
	public void setHakedilenGunSayisi(int hakedilenGunSayisi) {
		this.hakedilenGunSayisi = hakedilenGunSayisi;
	}
	public int getMevcutYilIciHakedilenGunSayisi() {
		return mevcutYilIciHakedilenGunSayisi;
	}
	public void setMevcutYilIciHakedilenGunSayisi(int mevcutYilIciHakedilenGunSayisi) {
		this.mevcutYilIciHakedilenGunSayisi = mevcutYilIciHakedilenGunSayisi;
	}


}
