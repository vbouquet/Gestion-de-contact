package servletAction.loader;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import service.ContactService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GroupAddContact extends Action{

    public ActionForward execute(final ActionMapping pMapping, ActionForm pForm,
                                 final HttpServletRequest pRequest, final HttpServletResponse pResponse) {

        final ContactService cs = new ContactService();
        final Object contacts = cs.loadContacts();
        final Object groups = cs.loadGroups();

        if (contacts instanceof List && groups instanceof List) {
            pRequest.setAttribute("contacts", contacts);
            pRequest.setAttribute("groups", groups);
            return pMapping.findForward("success");
        }
        else {
            return pMapping.findForward("error");
        }
    }
}