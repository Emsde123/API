package mealB;

public class OtherExpenseBuilder {

//    {
//        "name": "API",
//            "amount": 9999.00,
//            "expenseDateTime": "10/28/2022 00:00:00",
//            "expenseType": "Other",
//            "OtherExpenseNameId": 4
//    }

        private String name;
        private double amount;
        private String expenseDateTime;
        private String expenseType;
        private int otherExpenseNameId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getExpenseDateTime() {
        return expenseDateTime;
    }

    public void setExpenseDateTime(String expenseDateTime) {
        this.expenseDateTime = expenseDateTime;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public int getOtherExpenseNameId() {
        return otherExpenseNameId;
    }

    public void setOtherExpenseNameId(int otherExpenseNameId) {
        this.otherExpenseNameId = otherExpenseNameId;
    }
}
