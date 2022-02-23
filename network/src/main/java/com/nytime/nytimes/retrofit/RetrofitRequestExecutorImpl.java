package com.nytime.nytimes.retrofit;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import com.nytime.network.BuildConfig;
import com.nytime.network.R;
import com.nytime.nytimes.AdibNetworkClient;
import com.nytime.nytimes.ApiResponseListener;
import com.nytime.nytimes.BaseRequest;
import com.nytime.nytimes.RetrofitProvider;
import com.nytime.nytimes.client.RequestExecutor;
import com.nytime.nytimes.client.Util;
import com.nytime.nytimes.client.request.ApiResponse;
import com.nytime.nytimes.client.request.ErrorResponse;
import com.nytime.nytimes.utils.RequestMethod;
import com.nytime.nytimes.utils.NetworkUtils;

import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * //FIX-ME We need to create a seperate response parser class common between all request executors
 *
 * @author zuhaib ahmed
 */
public class RetrofitRequestExecutorImpl implements RequestExecutor {

    private static final String TAG = RetrofitRequestExecutorImpl.class.getSimpleName();

    public Call<String> createRequest(BaseRequest request) {
        Call<String> call;
        ApiInterface apiInterface = RetrofitProvider.INSTANCE.getInstance().create(ApiInterface.class);
        switch (request.requestMethod()) {
            default:
            case RequestMethod.GET:
                call = apiInterface.getRequest(request.endpoint(), BuildConfig.API_KEY);
                break;


        }
        return call;
    }

    @Override
    public <T> void execute(BaseRequest request, ApiResponseListener<T> listener) {
        Call<String> call = createRequest(request);
        Log.d(TAG, "execute() PostBody: " + request.postBody());
        Log.d(TAG, "execute() QueryParams: " + request.getQueryParams());

        if (NetworkUtils.isNetworkAvailable(AdibNetworkClient.Companion.getContext())) {

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    try {
                        parseSuccessResponse(request, listener, response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable throwable) {
                    ErrorResponse errorResponse = getDefaultErrorResponse(request);
                    listener.onApiFailure(errorResponse);
                }
            });
        } else {
            ErrorResponse errorResponse = new ErrorResponse(Util.INSTANCE.getERROR_CODE_NETWORK_ERROR());
            errorResponse.setErrorTitle(AdibNetworkClient.Companion.getContext().getString(R.string.error));
            errorResponse.setErrorMessage(AdibNetworkClient.Companion.getContext().getString(R.string.error_please_check_network_connection));
            listener.onApiFailure(errorResponse);
        }
    }


    private <T> void parseSuccessResponse(BaseRequest request, ApiResponseListener<T> listener, Response<String> response) throws JsonParseException, JSONException {
        ApiResponse<T> apiResponse = new ApiResponse<T>();
        apiResponse.setResponseCode(response.code());
        apiResponse.setData(new Gson().fromJson(response.body(), request.responseType()));
        listener.onApiSuccess(apiResponse);
    }

    private ErrorResponse getDefaultErrorResponse(BaseRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorTitle(AdibNetworkClient.Companion.getContext().getString(R.string.error));
        errorResponse.setErrorMessage(AdibNetworkClient.Companion.getContext().getString(R.string.generic_network_error));
        return errorResponse;
    }

}






