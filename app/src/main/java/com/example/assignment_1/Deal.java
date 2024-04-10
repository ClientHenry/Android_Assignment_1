package com.example.assignment_1;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_deal",
        foreignKeys = {
                @ForeignKey(entity = Product.class,
                        parentColumns = "pid",
                        childColumns = "productId",
                        onDelete = ForeignKey.NO_ACTION),
                @ForeignKey(entity = Customer.class,
                        parentColumns = "cid",
                        childColumns = "customerId",
                        onDelete = ForeignKey.NO_ACTION)
        },
        indices= {
        @Index("productId"),
        @Index("customerId")
        }
)

public class Deal {

    @PrimaryKey(autoGenerate = true)
    public int did;

    public int productId;
    public int customerId;


    public Deal(int productId, int customerId) {
        this.productId = productId;
        this.customerId = customerId;
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }
}
