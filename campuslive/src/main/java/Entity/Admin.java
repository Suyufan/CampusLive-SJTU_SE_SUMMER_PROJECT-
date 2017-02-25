package Entity;

import javax.persistence.*;

/**
 * Created by 昱凡 on 2016/7/21.
 */
@Entity
public class Admin {
    private int adminId;
    private String adminName;
    private String password;
    private String email;
    private String telephone;

    @Id
    @Column(name = "adminId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "adminName")
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        if (adminId != admin.adminId) return false;
        if (adminName != null ? !adminName.equals(admin.adminName) : admin.adminName != null) return false;
        if (password != null ? !password.equals(admin.password) : admin.password != null) return false;
        if (email != null ? !email.equals(admin.email) : admin.email != null) return false;
        if (telephone != null ? !telephone.equals(admin.telephone) : admin.telephone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adminId;
        result = 31 * result + (adminName != null ? adminName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        return result;
    }
}
