package models;

import javax.persistence.*;

/**
 * <p>Project: financeplatform</p>
 * <p>Title: AutherTication.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
@Entity
@Table(name = "c_authentication")
@NamedQueries({
        @NamedQuery(name="findCustomerByAuthentication",query="select c from Authentication a,Customer c where c.authenticationId = a.id and a.userName = :userName and a.password = :password"),
        @NamedQuery(name="findCustomerByUserName",query="select c from Authentication a,Customer c where c.authenticationId = a.id and a.userName = :userName"),
        @NamedQuery(name="findAuthentication",query="select a from Authentication a where a.userName = :userName and a.password = :password")
})
public class Authentication extends BaseEntity{
    @Column(length = 20, name = "user_name")
    private String userName;
    @Column(length = 50)
    private String password;
    @Column(length = 10)
    private String channel;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}