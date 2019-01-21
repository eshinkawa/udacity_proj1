package project1.udacity.eduardoshinkawa.myapplication;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieTrailers
{
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("results")
    @Expose
    private List<MovieVideos> results = null;


    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public List<MovieVideos> getResults ()
    {
        return results;
    }

    public void setResults (List<MovieVideos> results)
    {
        this.results = results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", results = "+results+"]";
    }

    public class MovieVideos {

        @SerializedName("site")
        @Expose
        private String site;

        @SerializedName("id")
        @Expose
        private String id;

        @SerializedName("iso_639_1")
        @Expose
        private String iso_639_1;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("type")
        @Expose
        private String type;

        @SerializedName("key")
        @Expose
        private String key;

        @SerializedName("iso_3166_1")
        @Expose
        private String iso_3166_1;

        @SerializedName("size")
        @Expose
        private String size;

        public String getSite ()
        {
            return site;
        }

        public void setSite (String site)
        {
            this.site = site;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getIso_639_1 ()
        {
            return iso_639_1;
        }

        public void setIso_639_1 (String iso_639_1)
        {
            this.iso_639_1 = iso_639_1;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        public String getType ()
        {
            return type;
        }

        public void setType (String type)
        {
            this.type = type;
        }

        public String getKey ()
        {
            return key;
        }

        public void setKey (String key)
        {
            this.key = key;
        }

        public String getIso_3166_1 ()
        {
            return iso_3166_1;
        }

        public void setIso_3166_1 (String iso_3166_1)
        {
            this.iso_3166_1 = iso_3166_1;
        }

        public String getSize ()
        {
            return size;
        }

        public void setSize (String size)
        {
            this.size = size;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [site = "+site+", id = "+id+", iso_639_1 = "+iso_639_1+", name = "+name+", type = "+type+", key = "+key+", iso_3166_1 = "+iso_3166_1+", size = "+size+"]";
        }
    }
}

