package ntattuan.vvhieu.cuoikyltdd02.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Candidate {
    private int Id;
    private String Name;
    private String CMND;
    private int Gender;
    private byte[] Avatar;
    private boolean HoiPhi;
    private boolean DoanPhi;

    public Candidate() {

    }

    public Candidate(String name, String CMND, int gender) {
        Name = name;
        this.CMND = CMND;
        Gender = gender;
    }

    public Candidate(int id, String name, String CMND, int gender) {
        Id = id;
        Name = name;
        this.CMND = CMND;
        Gender = gender;
    }

    public Candidate(int id, String name, String CMND, int gender, byte[] avatar) {
        Id = id;
        Name = name;
        this.CMND = CMND;
        Gender = gender;
        Avatar = avatar;
    }
    public Candidate(String name, String CMND, int gender, byte[] avatar) {
        Name = name;
        this.CMND = CMND;
        Gender = gender;
        Avatar = avatar;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public int getGender() {
        return Gender;
    }
    public String getGenderToString() {
        if (Gender == 1) return "Nam";
        return "Nữ";
    }
    public void setGender(int gender) {
        Gender = gender;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public byte[] getAvatar() {
        return Avatar;
    }
    public Bitmap getAvatarToBitMap() {
        byte[] image = this.getAvatar();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        return bitmap;
    }
    public void setAvatar(byte[] avatar) {
        Avatar = avatar;
    }

    public boolean isHoiPhi() {
        return HoiPhi;
    }

    public void setHoiPhi(boolean hoiPhi) {
        HoiPhi = hoiPhi;
    }

    public boolean isDoanPhi() {
        return DoanPhi;
    }

    public void setDoanPhi(boolean doanPhi) {
        DoanPhi = doanPhi;
    }

}
