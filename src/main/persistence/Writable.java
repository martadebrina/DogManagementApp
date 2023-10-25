package persistence;

import org.json.JSONObject;

// The implementation within this class is adapted from the "JsonSerializationDemo" project
// (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
