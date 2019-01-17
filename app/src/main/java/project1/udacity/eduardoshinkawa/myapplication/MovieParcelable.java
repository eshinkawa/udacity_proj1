package project1.udacity.eduardoshinkawa.myapplication;
import android.os.Parcel;
import android.os.Parcelable;

public class MovieParcelable implements Parcelable {

    private String originalTitle;
    private String overview;
    private String releaseDate;
    private Double voteAverage;
    private String posterPath;

    // Constructor
    public MovieParcelable( String originalTitle, String overview, Double voteAverage, String releaseDate, String posterPath){
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.posterPath = posterPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String title) {
        this.originalTitle = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return overview;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }
    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    // Parcelling part
    public MovieParcelable(Parcel in){
        this.originalTitle = in.readString();
        this.overview =  in.readString();
        this.voteAverage =  in.readDouble();
        this.releaseDate =  in.readString();
        this.posterPath =  in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.originalTitle);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeString(this.posterPath);
        dest.writeDouble(this.voteAverage);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MovieParcelable createFromParcel(Parcel in) {
            return new MovieParcelable(in);
        }

        public MovieParcelable[] newArray(int size) {
            return new MovieParcelable[size];
        }
    };
}

