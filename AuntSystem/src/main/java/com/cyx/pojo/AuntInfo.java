package com.cyx.pojo;

public class AuntInfo {
    private String auntId;
    private String resgiterId;
    private String auntName;
    private int auntHasorder;
    private String auntSex;
    private String auntAddress;
    private String auntIdentity;
    private String auntIdentityimage;
    private String auntIdentityibackamge;
    private String auntPhoneno;
    private String auntCount;
    private String auntPassword;
    private String auntPositon;
    private Integer auntParttimejob;
    private Integer auntState;

    public String getAuntId() {
        return auntId;
    }

    public int getAuntHasorder() {
		return auntHasorder;
	}

	public void setAuntHasorder(int auntHasorder) {
		this.auntHasorder = auntHasorder;
	}

	public String getResgiterId() {
		return resgiterId;
	}

	public void setResgiterId(String resgiterId) {
		this.resgiterId = resgiterId;
	}

	public void setAuntId(String auntId) {
        this.auntId = auntId == null ? null : auntId.trim();
    }

    public String getAuntName() {
        return auntName;
    }

    public void setAuntName(String auntName) {
        this.auntName = auntName == null ? null : auntName.trim();
    }

    public String getAuntSex() {
        return auntSex;
    }

    public void setAuntSex(String auntSex) {
        this.auntSex = auntSex == null ? null : auntSex.trim();
    }

    public String getAuntAddress() {
        return auntAddress;
    }

    public void setAuntAddress(String auntAddress) {
        this.auntAddress = auntAddress == null ? null : auntAddress.trim();
    }

    public String getAuntIdentity() {
        return auntIdentity;
    }

    public void setAuntIdentity(String auntIdentity) {
        this.auntIdentity = auntIdentity == null ? null : auntIdentity.trim();
    }

    public String getAuntIdentityimage() {
        return auntIdentityimage;
    }

    public void setAuntIdentityimage(String auntIdentityimage) {
        this.auntIdentityimage = auntIdentityimage == null ? null : auntIdentityimage.trim();
    }

    public String getAuntIdentityibackamge() {
        return auntIdentityibackamge;
    }

    public void setAuntIdentityibackamge(String auntIdentityibackamge) {
        this.auntIdentityibackamge = auntIdentityibackamge == null ? null : auntIdentityibackamge.trim();
    }

    public String getAuntPhoneno() {
        return auntPhoneno;
    }

    public void setAuntPhoneno(String auntPhoneno) {
        this.auntPhoneno = auntPhoneno == null ? null : auntPhoneno.trim();
    }

    public String getAuntCount() {
        return auntCount;
    }

    public void setAuntCount(String auntCount) {
        this.auntCount = auntCount == null ? null : auntCount.trim();
    }

    public String getAuntPassword() {
        return auntPassword;
    }

    public void setAuntPassword(String auntPassword) {
        this.auntPassword = auntPassword == null ? null : auntPassword.trim();
    }

    public String getAuntPositon() {
        return auntPositon;
    }

    public void setAuntPositon(String auntPositon) {
        this.auntPositon = auntPositon == null ? null : auntPositon.trim();
    }

    public Integer getAuntParttimejob() {
        return auntParttimejob;
    }

    public void setAuntParttimejob(Integer auntParttimejob) {
        this.auntParttimejob = auntParttimejob;
    }

    public Integer getAuntState() {
        return auntState;
    }

    public void setAuntState(Integer auntState) {
        this.auntState = auntState;
    }

	@Override
	public String toString() {
		return "AuntInfo [auntId=" + auntId + ", resgiterId=" + resgiterId
				+ ", auntName=" + auntName + ", auntHasorder=" + auntHasorder
				+ ", auntSex=" + auntSex + ", auntAddress=" + auntAddress
				+ ", auntIdentity=" + auntIdentity + ", auntIdentityimage="
				+ auntIdentityimage + ", auntIdentityibackamge="
				+ auntIdentityibackamge + ", auntPhoneno=" + auntPhoneno
				+ ", auntCount=" + auntCount + ", auntPassword=" + auntPassword
				+ ", auntPositon=" + auntPositon + ", auntParttimejob="
				+ auntParttimejob + ", auntState=" + auntState + "]";
	}
}