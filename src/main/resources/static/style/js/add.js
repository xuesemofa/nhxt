
/**
 * iframe自适应高度，height为手动设置的最小高度
 */
// function SetCwinHeight(){
//var iframeid=document.getElementById("iframeid"); //iframe id
//if (document.getElementById){
//	   if (iframeid && !window.opera){
//		    if (iframeid.contentDocument && iframeid.contentDocument.body.offsetHeight){
//			     iframeid.height = iframeid.contentDocument.body.offsetHeight;
//			    }else if(iframeid.Document && iframeid.Document.body.scrollHeight){
//			     iframeid.height = iframeid.Document.body.scrollHeight;
//			    }
//		   }
//	  }
// }

 function changeFrameHeight(){
    var ifm1= document.getElementById("iframepage"); 
    var ifm= document.getElementById("iframeid"); 
    ifm.height=document.documentElement.clientHeight-120;
    ifm1.style.height=document.documentElement.clientHeight-120+"px";
}changeFrameHeight()