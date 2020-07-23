package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import dal.ProductDAO;
import model.ProductGroup;

public final class productgroup_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            int id = Integer.parseInt(request.getParameter("id"));
            ProductDAO da = new ProductDAO();
            ProductGroup group = da.getPGbyID(id);
            request.setAttribute("group", group);
        
      out.write("\n");
      out.write("\n");
      out.write("        <div class=\"m-5\">\n");
      out.write("            <div class=\"mt-5 mx-5 text-tsunami\"> Home | ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.category.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" | ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.brand.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</div>\n");
      out.write("            <div class=\"border-dark mb-5 mt-2 mx-5 row\" style=\"background: linen\">\n");
      out.write("                <div class=\"col-4 m-5\">\n");
      out.write("                    <div class=\"mb-3\">\n");
      out.write("                        <img id=\"imgMain\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.images.get(0)}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" alt=\"imgCover\" style=\"width: 100%; height: 450px;\">\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"d-flex justify-content-between\">\n");
      out.write("                        <img class=\"imgComp\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.images.get(1)}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" alt=\"imgCover\" style=\"width: 75px; height: 75px;\"\n");
      out.write("                             onmouseenter=\"mouseEnterImg(this)\" onmouseleave=\"mouseLeaveImg()\" onmousedown=\"mouseDownImg(this)\">\n");
      out.write("                        <img class=\"imgComp\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.images.get(2)}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" alt=\"imgCover\" style=\"width: 75px; height: 75px;\"\n");
      out.write("                             onmouseenter=\"mouseEnterImg(this)\" onmouseleave=\"mouseLeaveImg()\" onmousedown=\"mouseDownImg(this)\">\n");
      out.write("                        <img class=\"imgComp\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.images.get(3)}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" alt=\"imgCover\" style=\"width: 75px; height: 75px;\"\n");
      out.write("                             onmouseenter=\"mouseEnterImg(this)\" onmouseleave=\"mouseLeaveImg()\" onmousedown=\"mouseDownImg(this)\">\n");
      out.write("                        <img class=\"imgComp\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.images.get(4)}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" alt=\"imgCover\" style=\"width: 75px; height: 75px;\"\n");
      out.write("                             onmouseenter=\"mouseEnterImg(this)\" onmouseleave=\"mouseLeaveImg()\" onmousedown=\"mouseDownImg(this)\">\n");
      out.write("                        <img class=\"imgComp\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.images.get(5)}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" alt=\"imgCover\" style=\"width: 75px; height: 75px;\"\n");
      out.write("                             onmouseenter=\"mouseEnterImg(this)\" onmouseleave=\"mouseLeaveImg()\" onmousedown=\"mouseDownImg(this)\">\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"col-6 my-5\">\n");
      out.write("                    <h3 class=\"text-dark\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</h3>\n");
      out.write("                    <p class=\"d-inline mr-2 text-tsunami\">4.9/5</p><span class=\"text-tsunami\">&#9733; &#9733; &#9733; &#9733; &#9734;</span>\n");
      out.write("                    <p class=\"d-inline ml-2 pl-2 border-left border-secondary\">1.7k Sold</p>\n");
      out.write("                    <div class=\"rounded my-3 p-4\" style=\"background: whitesmoke\">\n");
      out.write("                        <h3 class=\"text-tsunami\">20000 đ </h3>\n");
      out.write("                        <i class=\"fas fa-money-bill-wave\"></i> Where is cheaper then go out there to buy\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"m-2 row\">\n");
      out.write("                        <p class=\"d-inline col-3 text-muted\">Shop</p>\n");
      out.write("                        <a href=\"\" class=\"d-inline col-7\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.shop.brand}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</a>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"m-2 row\">\n");
      out.write("                        <p class=\"d-inline col-3 text-muted\">Description</p>\n");
      out.write("                        <p class=\"d-inline col-7\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.description}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"m-2 row\">\n");
      out.write("                        <p class=\"d-inline col-3 text-muted\">State</p>\n");
      out.write("                        <p class=\"d-inline col-7\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.state}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <form>\n");
      out.write("                        <div class=\"m-2 row\">\n");
      out.write("                            <p class=\"d-inline col-3 text-muted\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.classify1.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("                            <div class=\"col-7 btn-item-1\" >\n");
      out.write("                                ");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                                <input id=\"item1\" type=\"hidden\" name=\"item1ID\"> \n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <script src=\"https://use.fontawesome.com/56a1666710.js\"></script>\n");
      out.write("        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js\"></script>\n");
      out.write("        <script src=\"js/productgroupJS.js\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${group.classify1.list}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_0.setVar("item");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("                                    <button type=\"button\" class=\"btn btn-light\" style=\"width:100px; height: 45px\" \n");
          out.write("                                            onmousedown=\"clickBtnItem1(this, ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${item.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write(',');
          out.write(' ');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${item.img}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write(")\">\n");
          out.write("                                        ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${item.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\n");
          out.write("                                    </button>\n");
          out.write("                                ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }
}
