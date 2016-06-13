package com.mobilyte.chartadvice.Api;

/**
 * Created by mohit on 7/6/16.
 */
public interface Apis {
    String login = "http://www.chartadvise.com/amember/webservice";
    String loginTag = "login";

    String service_url = "http://www.chartadvise.com/amember/products";
    String serviceTag = "service_data";

    String trader_url = "http://www.chartadvise.com/web-services/traders_central.php";
    String trader_central_tag = "traderCentral";

    String trader_center_archives = "http://www.chartadvise.com/web-services/traders_central_archives.php";
    String trader_center_archives_tag = "Archive_data";

    String trader_center_filter_url = "http://www.chartadvise.com/web-services/traders_central.php";
    String trader_center_filter_tag = "traderCenterFilter";

    String nifty_url = "http://www.chartadvise.com/web-services/nifty_edge.php?\" + \"token=a152e84173914146e4bc4f391sd0f686ebc4f31&product_name=Nifty_Edge&id=4&offset=0&limit=10";
    String nifty_tag = "nifty";

    String about_us_url = "http://www.chartadvise.com/about-us/";
    String nifty_edge_url = "http://www.chartadvise.com/advisory/#tab-1386262353-1-65";
    String future_gain_url = "http://www.chartadvise.com/advisory/#tab-1386262353-2-62";
    String option_gain_url = "http://www.chartadvise.com/advisory/#tab-1412752482990-4-9";
    String equity_gain_url = "http://www.chartadvise.com/advisory/#tab-1386262487795-8-1";
    String trader_center_url = "http://www.chartadvise.com/advisory/#tab-1441004675463-6-3";

}
