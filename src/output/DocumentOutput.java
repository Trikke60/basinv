package output;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import model.Address;
import model.Business;
import model.Customer;
import model.Invoice;
import model.Quote;
import persistency.RDBConnection;
import persistency.controller.AddressController;
import persistency.controller.CodeController;
import persistency.controller.CustomerController;
import utilities.Constants;
import utilities.Figures;
import utilities.FixTypes;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public abstract class DocumentOutput
{
	private Customer head;
	private Address addressHead;
	private double totalExcl = 0f;
	private double totalIncl = 0f;
	private double totalVat6 = 0f;
	private double totalVat21 = 0f;
	private double totalVat = 0f;
	private int lineCounter = Integer.parseInt(RDBConnection.getProps().getProperty(Constants.DOCUMENT_LINES));
	private boolean vat;
	private Customer customer;

	protected StringBuilder strManyDirectories = new StringBuilder();
	/** The resulting PDF file. */

	public static final Font[] FONT = new Font[24];
	public static final BaseColor BORDER_COLOR = BaseColor.BLUE;
	public static final BaseColor TITLE_COLOR = BaseColor.BLUE;
	public static final BaseColor TEXT_COLOR = BaseColor.BLUE;
	static
	{
		FONT[0] = new Font(FontFamily.HELVETICA, 18, Font.BOLD, TITLE_COLOR);
		FONT[1] = new Font(FontFamily.HELVETICA, 16, Font.BOLD, TITLE_COLOR);
		FONT[2] = new Font(FontFamily.HELVETICA, 14, Font.ITALIC);
		FONT[3] = new Font(FontFamily.HELVETICA, 12, Font.BOLD, TEXT_COLOR);
		FONT[4] = new Font(FontFamily.HELVETICA, 12, Font.ITALIC);
		FONT[5] = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, TEXT_COLOR);
		FONT[6] = new Font(FontFamily.HELVETICA, 8, Font.BOLD, TEXT_COLOR);
		FONT[7] = new Font(FontFamily.HELVETICA, 8, Font.ITALIC, TEXT_COLOR);
		FONT[8] = new Font(FontFamily.HELVETICA, 8, Font.NORMAL, TEXT_COLOR);
		FONT[9] = new Font(FontFamily.HELVETICA, 7, Font.BOLD);
		FONT[10] = new Font(FontFamily.HELVETICA, 7, Font.ITALIC);
		FONT[11] = new Font(FontFamily.HELVETICA, 7);
		FONT[12] = new Font(FontFamily.HELVETICA, 6, Font.BOLD);
		FONT[13] = new Font(FontFamily.HELVETICA, 6, Font.ITALIC);
		FONT[14] = new Font(FontFamily.HELVETICA, 6);
		FONT[15] = new Font(FontFamily.HELVETICA, 12, Font.BOLD);
		FONT[16] = new Font(FontFamily.HELVETICA, 12, Font.ITALIC);
		FONT[17] = new Font(FontFamily.HELVETICA, 12);
		FONT[18] = new Font(FontFamily.HELVETICA, 8, Font.BOLD);
		FONT[19] = new Font(FontFamily.HELVETICA, 8, Font.ITALIC);
		FONT[20] = new Font(FontFamily.HELVETICA, 8);
		FONT[21] = new Font(FontFamily.HELVETICA, 10, Font.BOLD);
		FONT[22] = new Font(FontFamily.HELVETICA, 10, Font.ITALIC);
		FONT[23] = new Font(FontFamily.HELVETICA, 10);
	}

	/**
	 * Owner Header
	 * 
	 * @return Table
	 */
	public PdfPTable createHeader(final String TITLE, final String docId)
	{
		head = getOwnCompany();

		// a table with three columns
		final PdfPTable table = new PdfPTable(10);
		// the cell object
		PdfPCell cell;
		// now we add a cell with rowspan 4 for the logo
		try
		{
			cell = new PdfPCell(Image.getInstance(String.format(Constants.IMG_RESOURCE, Constants.LOGO)));
			cell.setRowspan(4);
			cell.setColspan(2);// span 2 cell
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

		}
		catch (final BadElementException e)
		{
			e.printStackTrace();
		}
		catch (final MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}

		// row 1
		cell = new PdfPCell(new Phrase(head.getCusActivity(), FONT[3]));
		cell.setColspan(3);// span 3 cell
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(TITLE, FONT[2]));
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(docId, FONT[4]));
		cell.setColspan(3);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// row 2
		cell = new PdfPCell(new Phrase(addressHead.getAddStreet() + Constants.BLANK + addressHead.getAddNumber() + (addressHead.getAddBox().equals(Constants.EMPTY) ? Constants.EMPTY : " bus " + addressHead.getAddBox()), FONT[8]));
		cell.setColspan(8);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// row 3
		cell = new PdfPCell(new Phrase(addressHead.getAddZip() + Constants.BLANK + addressHead.getAddCity() + Constants.PERIOD + CodeController.getOneCodeDetail(Constants.COUNTRY_CODE, addressHead.getAddCountry()).getCodeDesc(), FONT[8]));
		cell.setColspan(8);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// row 4
		if (!getHead().getCusVat().equals(Constants.EMPTY))
		{
			cell = new PdfPCell(new Phrase(Constants.BTW_ + getHead().getCusVat(), FONT[8]));
		}
		else
		{
			cell = new PdfPCell(new Phrase(Constants.BLANK));
		}
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(8);
		table.addCell(cell);

		return table;
	}

	/**
	 * Customer Info
	 * 
	 * @return Table
	 */
	public PdfPTable createCustomerInfo(final Business business)
	{
		// a table with three columns
		final PdfPTable table = new PdfPTable(10);
		// the cell object
		PdfPCell cell = null;
		String id = null;
		if (business instanceof Invoice)
		{
			id = ((Invoice) business).getInvAddid();
		}
		if (business instanceof Quote)
		{
			id = ((Quote) business).getQteDlvAddid();
		}
		final Address address = AddressController.getAddress(id);
		// 10 cells per row
		// row 1
		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);

		if (!getHead().getCusPhone().equals(Constants.EMPTY))
		{
			cell = new PdfPCell(new Phrase(Constants.TELEPHONE + getHead().getCusPhone(), FONT[8]));
		}
		else
		{
			cell = new PdfPCell(new Phrase(Constants.BLANK));
		}
		cell.setColspan(8);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// row 2
		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);

		if (!getHead().getCusMobile().equals(Constants.EMPTY))
		{
			cell = new PdfPCell(new Phrase(Constants.MOBILE + getHead().getCusMobile(), FONT[8]));
		}
		else
		{
			cell = new PdfPCell(new Phrase(Constants.BLANK));
		}
		cell.setColspan(3);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(customer.getCusName(), FONT[17]));
		cell.setColspan(5);
		cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// row 2
		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);

		if (!getHead().getCusFax().equals(Constants.EMPTY))
		{
			cell = new PdfPCell(new Phrase(Constants.FAX + getHead().getCusFax(), FONT[8]));
		}
		else
		{
			cell = new PdfPCell(new Phrase(Constants.BLANK));
		}
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(address.getAddStreet() + Constants.BLANK + address.getAddNumber() + (address.getAddBox().equals(Constants.EMPTY) ? Constants.EMPTY : " bus " + address.getAddBox()), FONT[17]));
		cell.setColspan(5);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// row 3
		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(address.getAddZip() + Constants.BLANK + address.getAddCity() + Constants.PERIOD + CodeController.getOneCodeDetail(Constants.COUNTRY_CODE, address.getAddCountry()).getCodeDesc(), FONT[17]));
		cell.setColspan(5);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		// row 4
		cell = new PdfPCell(new Phrase(getHead().getCusName(), FONT[1]));
		cell.setColspan(5);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(Constants.BTW, FONT[18]));
		cell.setColspan(1);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(customer.getCusVat(), FONT[20]));
		cell.setColspan(4);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		return table;
	}

	/**
	 * @return
	 */
	public Customer getOwnCompany()
	{
		final Collection<Business> list = CustomerController.getCustomers(new String[] { Constants.EMPTY, Constants.HEAD, "true" });
		final Object[] heads = list.toArray();
		// only 1 head company allowed
		head = (Customer) heads[0];
		addressHead = getOwnAddress(head);
		return head;
	}

	/**
	 * @param head
	 */
	public Address getOwnAddress(final Customer head)
	{
		Address addressHead = null;
		final ArrayList<Address> list = new ArrayList<Address>();
		if (head != null)
		{
			final String[] filter = { head.getIdCus(), FixTypes.INVOICE_ADRESS_TYPE, "true" };

			final Collection<Business> businessList = AddressController.getAddresses(filter);
			final Iterator<Business> it = businessList.iterator();

			while (it.hasNext())
			{
				addressHead = (Address) it.next();
				list.add(addressHead);
			}

			if (!list.isEmpty())
			{
				addressHead = list.get(0);
			}
		}
		return addressHead;
	}

	/**
	 * @return table
	 */
	public PdfPTable createDetails()
	{
		final PdfPTable table = new PdfPTable(25);
		// the cell object
		// title
		detailsTitle(table);

		// create a detail line
		lineCounter = createOneDetailLine(table, lineCounter);
		// fill up until 25 lines
		fillUpEmptyLines(table, lineCounter);

		return table;

	}

	/**
	 * @param table
	 * @param lineCounter
	 * @return
	 */
	public abstract int createOneDetailLine(PdfPTable table, int lineCounter);

	/**
	 * @param table
	 * @param lineCounter
	 */
	public void fillUpEmptyLines(final PdfPTable table, int lineCounter)
	{
		PdfPCell cell;
		while (lineCounter-- > 0)
		{
			cell = new PdfPCell(new Phrase(Constants.BLANK, FONT[20]));
			cell.setColspan(14);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase());
			table.addCell(cell);

			cell = new PdfPCell(new Phrase());
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase());
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase());
			cell.setColspan(3);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase());
			cell.setColspan(3);
			table.addCell(cell);
		}
	}

	/**
	 * Subcontractor not VAT obliged
	 * 
	 * @param table
	 */
	public void noVatContractor(final PdfPTable table)
	{
		PdfPCell cell;
		cell = new PdfPCell(new Phrase(Constants.CO_CONTRACTOR, FONT[10]));
		cell.setColspan(14);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(Constants.BLANK));
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setColspan(3);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setColspan(3);
		table.addCell(cell);
	}

	/**
	 * Purpose: VAT Deduction for CN
	 * @param table
	 */
	public void vatCNDeduction(final PdfPTable table)
	{
		PdfPCell cell;
		cell = new PdfPCell(new Phrase(Constants.CN_BTW_VERMINDERING, FONT[10]));
		cell.setColspan(14);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(Constants.BLANK));
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setColspan(3);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setColspan(3);
		table.addCell(cell);
	}

	/**
	 * Create title line for quote details
	 * 
	 * @param table
	 */
	public void detailsTitle(final PdfPTable table)
	{
		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Post", FONT[6]));
		cell.setColspan(14);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("EH", FONT[6]));
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Hoev.", FONT[6]));
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("BTW", FONT[6]));
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("EP", FONT[6]));
		cell.setColspan(3);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Totaal", FONT[6]));
		cell.setColspan(3);
		table.addCell(cell);
	}

	/**
	 * Creates a PDF with information about the movies
	 * 
	 * @param filename
	 *            the name of the PDF file that will be created.
	 * @throws DocumentException
	 * @throws IOException
	 */

	public PdfPTable createFooter()
	{
		final PdfPTable table = new PdfPTable(25);
		// row 1
		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Betaalwijze", FONT[9]));
		cell.setColspan(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(Constants.BLANK, FONT[9]));
		cell.setColspan(12);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Totaal (Excl)", FONT[9]));
		cell.setColspan(3);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(String.format("%10.2f", getTotalExcl()) + Constants.EURO, FONT[15]));
		cell.setColspan(5);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);

		// row 2
		cell = new PdfPCell(new Phrase("Rekeningnummer", FONT[10]));
		cell.setColspan(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(head.getCusAccount(), FONT[9]));
		cell.setColspan(12);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("BTW", FONT[18]));
		cell.setColspan(3);
		table.addCell(cell);

		setTotalVat(getTotalVat6() + getTotalVat21());
		cell = new PdfPCell(new Phrase(String.format("%10.2f", getTotalVat()) + Constants.EURO, FONT[18]));
		cell.setColspan(5);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);

		// row 3
		cell = new PdfPCell(new Phrase(Constants.EMPTY));
		cell.setColspan(18);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(Figures.SIX + Constants.PERCENTAGE, FONT[18]));
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(String.format("%10.2f", getTotalVat6()) + Constants.EURO, FONT[18]));
		cell.setColspan(5);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);

		// row 4
		cell = new PdfPCell(new Phrase(Constants.EMPTY));
		cell.setColspan(18);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(Figures.TWENTYONE + Constants.PERCENTAGE, FONT[18]));
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(String.format("%10.2f", getTotalVat21()) + Constants.EURO, FONT[18]));
		cell.setColspan(5);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);

		// row 5
		cell = new PdfPCell(new Phrase());
		cell.setColspan(25);
		table.addCell(cell);

		// row 6

		cell = new PdfPCell(new Phrase(Constants.BLANK));
		cell.setColspan(17);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Totaal", FONT[15]));
		cell.setColspan(3);
		table.addCell(cell);

		setTotalIncl(getTotalExcl() + getTotalVat());
		cell = new PdfPCell(new Phrase(String.format("%10.2f", getTotalIncl()) + Constants.EURO, FONT[15]));
		cell.setColspan(5);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(Constants.DELIVERY_CONDITIONS + "\n", FONT[13]));
		cell.setColspan(25);
		cell.setRowspan(12);
		cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase());
		cell.setColspan(25);
		cell.setBorderWidthBottom(2f);
		cell.setBorderColorBottom(BORDER_COLOR);
		table.addCell(cell);

		return table;

	}


	public int getLineCounter()
	{
		return lineCounter;
	}

	public void setLineCounter(final int lineCounter)
	{
		this.lineCounter = lineCounter;
	}

	public double getTotalExcl()
	{
		return totalExcl;
	}

	public void setTotalExcl(final double totalExcl)
	{
		this.totalExcl = totalExcl;
	}

	public double getTotalIncl()
	{
		return totalIncl;
	}

	public void setTotalIncl(final double totalIncl)
	{
		this.totalIncl = totalIncl;
	}

	public double getTotalVat21()
	{
		return totalVat21;
	}

	public void setTotalVat21(final double totalVat21)
	{
		this.totalVat21 = totalVat21;
	}

	public double getTotalVat6()
	{
		return totalVat6;
	}

	public void setTotalVat6(final double totalVat6)
	{
		this.totalVat6 = totalVat6;
	}

	public boolean isVat()
	{
		return vat;
	}

	public void setVat(final boolean vat)
	{
		this.vat = vat;
	}

	public double getTotalVat()
	{
		return totalVat;
	}

	public void setTotalVat(final double totalVat)
	{
		this.totalVat = totalVat;
	}

	public Customer getHead()
	{
		return head;
	}

	public void setHead(final Customer head)
	{
		this.head = head;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(final Customer customer)
	{
		this.customer = customer;
	}

}
