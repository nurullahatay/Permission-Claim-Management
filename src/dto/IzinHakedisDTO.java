package dto;

import java.util.Date;

public class IzinHakedisDTO {
	private long sicilno; // PersonelbilgisiDto'sunda var.
	private Date gecerliolacagitarih;
	private int hakedilengunsayisi;
	private int mevcutyilicihakedilengunsayisi;

	public long getSicilno() {
		return sicilno;
	}

	public void setSicilno(long sicilno) {
		this.sicilno = sicilno;
	}

	public Date getGecerliolacagitarih() {
		return gecerliolacagitarih;
	}

	public void setGecerliolacagitarih(Date gecerliolacagitarih) {
		this.gecerliolacagitarih = gecerliolacagitarih;
	}

	public int getHakedilengunsayisi() {
		return hakedilengunsayisi;
	}

	public void setHakedilengunsayisi(int hakedilengunsayisi) {
		this.hakedilengunsayisi = hakedilengunsayisi;
	}

	public int getMevcutyilicihakedilengunsayisi() {
		return mevcutyilicihakedilengunsayisi;
	}

	public void setMevcutyilicihakedilengunsayisi(int mevcutyilicihakedilengunsayisi) {
		this.mevcutyilicihakedilengunsayisi = mevcutyilicihakedilengunsayisi;
	}

}
