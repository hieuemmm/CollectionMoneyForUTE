package ntattuan.vvhieu.cuoikyltdd02.Model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ntattuan.vvhieu.cuoikyltdd02.App;

public class Candidate {
    private int Id;
    private String Name;
    private String CMND;
    private int Gender;
    private byte[] Avatar;
    private int IsActive;
    private boolean HoiPhi = false;
    private boolean DoanPhi = false;

    public Candidate() {

    }

    public Candidate(int id) {
        this.Id = id;
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

    public Candidate(String name, String CMND, int gender, byte[] avatar) {
        Name = name;
        this.CMND = CMND;
        Gender = gender;
        Avatar = avatar;
    }

    public Candidate(int id, String name, String CMND, int gender, byte[] avatar) {
        Id = id;
        Name = name;
        this.CMND = CMND;
        Gender = gender;
        Avatar = avatar;
    }

    public Candidate(String name, String CMND, int gender, byte[] avatar, int isActive) {
        Name = name;
        this.CMND = CMND;
        Gender = gender;
        Avatar = avatar;
        IsActive = isActive;
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
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        return bitmap;
    }

    public void setAvatar(byte[] avatar) {
        Avatar = avatar;
    }

    public void setIsActive(int isActive) {
        IsActive = isActive;
    }

    public int getIsActive() {
        return IsActive;
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


    public static List<Candidate> Sort(List<Candidate> listCandidate) {
        Collections.sort(listCandidate, new Comparator<Candidate>() {
            @Override
            public int compare(Candidate candidate1, Candidate candidate2) {
                String IsActive1 = candidate1.IsActive == App.ACTIVE ? "A" : "Z";
                String IsActive2 = candidate2.IsActive == App.ACTIVE ? "A" : "Z";
                String IsHoiPhi1 = candidate1.isHoiPhi() ? "Z" : "A";
                String IsHoiPhi2 = candidate2.isHoiPhi() ? "Z" : "A";
                String IsDoanPhi1 = candidate1.isDoanPhi() ? "Z" : "A";
                String IsDoanPhi2 = candidate1.isDoanPhi() ? "Z" : "A";
                int c = 0;
                c = IsActive1.compareTo(IsActive2);
                if (c == 0)
                    c = IsHoiPhi1.compareTo(IsHoiPhi2);
                if (c == 0)
                    c = IsDoanPhi1.compareTo(IsDoanPhi2);
                return c;
            }
        });
        return listCandidate;
    }
}
