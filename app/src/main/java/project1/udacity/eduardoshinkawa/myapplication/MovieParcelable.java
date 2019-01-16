package project1.udacity.eduardoshinkawa.myapplication;
import android.os.Parcel;
import android.os.Parcelable;

public class MovieParcelable implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MovieParcelable createFromParcel(Parcel in) {
            return new MovieParcelable(in);
        }

        public MovieParcelable[] newArray(int size) {
            return new MovieParcelable[size];
        }
    };

    private String title;
    private String overview;

    // Constructor
    public MovieParcelable( String title, String overview){
        this.title = title;
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    // Parcelling part
    public MovieParcelable(Parcel in){
        this.title = in.readString();
        this.overview =  in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.overview);
    }

    @Override
    public String toString() {
        return "MovieParcel{" +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }
}

