/**
 * 
 */
function sendReq() {
  var arg = getQueryParam();
  
  var url = $('#loginForm').attr('action');
  if (arg.p != null) {
    url += '?p=' + arg.p;
  }

  $('#loginForm').attr('action', url);
  $('#loginForm').submit();
}

function getQueryParam() {
  var arg = new Object;
  var pair = location.search.substring(1).split('&');
  for (var i = 0; pair[i]; i++) {
    var kv = pair[i].split('=');
    arg[kv[0]] = kv[1];
  }
  
  return arg;
}

function doLogout() {
  var proto = location.protocol;
  var host = location.host;

  var url = proto + '//' + host + '/logout';
  
  var arg = getQueryParam();
  if (arg.p != null) {
    url += '?p=' + arg.p;
  }
  
  location.href = url;
}