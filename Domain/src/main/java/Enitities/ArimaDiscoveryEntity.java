package Enitities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ArimaDiscovery", schema = "dbo")
public class ArimaDiscoveryEntity {
    private long id;
    private Integer p;
    private Integer q;
    private Integer d;
    private Integer predict1;
    private Integer predict2;
    private Integer predict3;
    private Integer value1;
    private Integer value2;
    private Integer value3;

    @Id
    @Column(name = "Id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "p")
    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    @Basic
    @Column(name = "q")
    public Integer getQ() {
        return q;
    }

    public void setQ(Integer q) {
        this.q = q;
    }

    @Basic
    @Column(name = "d")
    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }

    @Basic
    @Column(name = "predict1")
    public Integer getPredict1() {
        return predict1;
    }

    public void setPredict1(Integer predict1) {
        this.predict1 = predict1;
    }

    @Basic
    @Column(name = "predict2")
    public Integer getPredict2() {
        return predict2;
    }

    public void setPredict2(Integer predict2) {
        this.predict2 = predict2;
    }

    @Basic
    @Column(name = "predict3")
    public Integer getPredict3() {
        return predict3;
    }

    public void setPredict3(Integer predict3) {
        this.predict3 = predict3;
    }

    @Basic
    @Column(name = "value1")
    public Integer getValue1() {
        return value1;
    }

    public void setValue1(Integer value1) {
        this.value1 = value1;
    }

    @Basic
    @Column(name = "value2")
    public Integer getValue2() {
        return value2;
    }

    public void setValue2(Integer value2) {
        this.value2 = value2;
    }

    @Basic
    @Column(name = "value3")
    public Integer getValue3() {
        return value3;
    }

    public void setValue3(Integer value3) {
        this.value3 = value3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArimaDiscoveryEntity that = (ArimaDiscoveryEntity) o;
        return id == that.id && Objects.equals(p, that.p) && Objects.equals(q, that.q) && Objects.equals(d, that.d) && Objects.equals(predict1, that.predict1) && Objects.equals(predict2, that.predict2) && Objects.equals(predict3, that.predict3) && Objects.equals(value1, that.value1) && Objects.equals(value2, that.value2) && Objects.equals(value3, that.value3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, p, q, d, predict1, predict2, predict3, value1, value2, value3);
    }

}
