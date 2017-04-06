package shabalala.thamsanqa.tv_channel;

/**
 * Created by THAMMY on 4/4/2017.
 */

public class Config {

    //Address of our scripts of the CRUD
    public static final String URL_ADD = "http://thammy202.comli.com/addEmp.php";
    public static final String URL_GET_ALL = "http://thammy202.comli.com/getAllEmp.php";
    public static final String URL_GET_EMP = "http://thammy202.comli.com/getEmp.php?id=";
    public static final String URL_UPDATE_EMP = "http://thammy202.comli.com/updateEmp.php";
    public static final String URL_DELETE_EMP = "http://thammy202.comli.com/deleteEmp.php?id=";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_DATE = "date";
    public static final String TAG_TITLE = "title";
    public static final String TAG_TIME = "time";
    public static final String TAG_DESC = "description";
    public static final String TAG_ID = "show_id";

    //employee id to pass with intent
    public static final String SHOW_ID = "show_id";

}
