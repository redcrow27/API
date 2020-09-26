import org.json.JSONObject;

public class JsonObjectExample {
    public static void main(String[] args) {
        String s = "{\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Bhuvaneshwar Bandopadhyay\",\n" +
                "        \"email\": \"bhuvaneshwar_bandopadhyay@pouros.io\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"status\": true,\n" +
                "        \"created_at\": \"2020-09-23T03:50:03.625+05:30\",\n" +
                "        \"updated_at\": \"2020-09-23T03:50:03.625+05:30\"\n" +
                "    }";

        JSONObject object = new JSONObject(s);
        System.out.println(object.getInt("id"));
        System.out.println(object.getBoolean("status"));
        System.out.println(object.get("name"));
        System.out.println(object.get("created_at"));
        System.out.println(object.get("email"));
        System.out.println("-------------");
        System.out.println();

        String a = "{\n" +
                "            \"_id\": \"5f5ef8e68a9e7600179c537f\",\n" +
                "            \"id\": 20,\n" +
                "            \"name\": \"Selenium course\",\n" +
                "            \"duration\": \"6 months\",\n" +
                "            \"__v\": 0\n" +
                "        }";

        JSONObject obj = new JSONObject(a);
        System.out.println(obj.get("_id"));
        System.out.println(obj.getInt("id"));
        System.out.println(obj.get("name"));
        System.out.println(obj.get("duration"));
        System.out.println(obj.getInt("__v"));

    }
}
