package ee.track.model;

public class Data {
    private final String id;
    private final String name;
    private final String mark;
    private final String description;
    private final String year;
    private final String fuel;
    private final String gear;
    private final String price;

    private Data(DataBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.mark = builder.mark;
        this.description = builder.description;
        this.year = builder.year;
        this.fuel = builder.fuel;
        this.gear = builder.gear;
        this.price = builder.price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMark() {
        return mark;
    }

    public String getDescription() {
        return description;
    }

    public String getYear() {
        return year;
    }

    public String getFuel() {
        return fuel;
    }

    public String getGear() {
        return gear;
    }

    public String getPrice() {
        return price;
    }

    public static class DataBuilder {
        private String id;
        private String name;
        private String mark;
        private String description;
        private String year;
        private String fuel;
        private String gear;
        private String price;

        public DataBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public DataBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public DataBuilder setMark(String mark) {
            this.mark = mark;
            return this;
        }

        public DataBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public DataBuilder setYear(String year) {
            this.year = year;
            return this;
        }

        public DataBuilder setFuel(String fuel) {
            this.fuel = fuel;
            return this;
        }

        public DataBuilder setGear(String gear) {
            this.gear = gear;
            return this;
        }

        public DataBuilder setPrice(String price) {
            this.price = price;
            return this;
        }

        public Data build() {
            return new Data(this);
        }

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((fuel == null) ? 0 : fuel.hashCode());
        result = prime * result + ((gear == null) ? 0 : gear.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((mark == null) ? 0 : mark.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + ((year == null) ? 0 : year.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Data other = (Data) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (fuel == null) {
            if (other.fuel != null)
                return false;
        } else if (!fuel.equals(other.fuel))
            return false;
        if (gear == null) {
            if (other.gear != null)
                return false;
        } else if (!gear.equals(other.gear))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (mark == null) {
            if (other.mark != null)
                return false;
        } else if (!mark.equals(other.mark))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (year == null) {
            if (other.year != null)
                return false;
        } else if (!year.equals(other.year))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Data [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", mark=");
        builder.append(mark);
        builder.append(", description=");
        builder.append(description);
        builder.append(", year=");
        builder.append(year);
        builder.append(", fuel=");
        builder.append(fuel);
        builder.append(", gear=");
        builder.append(gear);
        builder.append(", price=");
        builder.append(price);
        builder.append("]");
        return builder.toString();
    }

}
