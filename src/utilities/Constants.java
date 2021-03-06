/**
 * 
 */
package utilities;

import org.apache.log4j.Logger;


/**
 * @author bematpae
 * 
 */
public interface Constants
{
	Logger logger = Logger.getLogger(Constants.class);
	String LOG_LEVEL = "logLevel";
	String SEPARATOR_SLASH = "/";
	String QUOTES_EXIST = " werd reeds verkocht, kan niet verwijderd worden!";
	String INVOICES_EXIST = " heeft reeds facturen, kan niet verwijderd worden!";
	String CREDIT_NOTE_NOT_ALLOWED = " kan niet gecrediteerd worden!";
	String QUOTE_CONFIRMED = " is reeds gefactureerd, kan niet verwijderd worden!";
	String EMPTY = "";
	String EXPIRED = "Vervallen";
	String PAYED = "Betaald";
	String SEARCH = "Zoek";
	String TRUE = "true";
	String NO_STREET = "Straat moet ingevuld zijn";
	String NO_ZIP = "Postcode moet ingevuld zijn";
	String NO_CODE = "Code moet ingevuld zijn!'";
	String NO_DESCRIPTION = "Omschrijving moet ingevuld zijn!'";
	String CODE_EXIST = "Code bestaat reeds!";
	String NO_NAME = "Naam moet ingevuld zijn";
	String NAME_CAN_NOT_BE_EMPTY = "Naam moet ingevuld zijn!";
	String MOBILE_NOT_CORRECT = "GSM moet van het formaat xxx/yyyyyy zijn";
	String PHONE_NOT_CORRECT = "Telefoon moet van het formaat xxx/yyyyyy zijn!";
	String EMAIL_NOT_CORRECT = "eMail moet van het formaat XXX@YYY.ZZZ zijn!";
	String VAT_CAN_NOT_BE_EMPTY = "BTW moet ingevuld zijn!";
	String VAT_NOT_CORRECT = "BTW is niet correct. Controleer BTW op: http://ec.europa.eu/taxation_customs/vies/vatResponse.html";
	String NO_INVOICE_ADDRESS = "Er moet een facturatie adres zijn!";
	String NO_CONTACTS = "Er moet ten minste één contactpersoon zijn!";
	String NO_TYPE = "Er moet een type geselecteerd zijn!";
	String NO_LANGUAGE = "Er moet een Taal geselecteerd zijn!";
	String NO_CURRENCY = "Er moet een muntcode geselecteerd zijn!";
	String NO_CATEGORY = "Er moet een categorie geselecteerd zijn!";
	String NO_PAYMENT = "Er moet een betalingsvoorwaarde geselecteerd zijn!";
	String NO_DELIVERY_ADDRESS = "Selecteer een werfadres!";
	String INV_DATE_FUTURE = "Factuur datum moet in de toekomst liggen!";
	String DUE_DATE_FUTURE = "Vervaldatum moet later dan factuurdatum zijn!";
	String NO_INV_DATE = "Selecteer een factuurdatum!";
	String NO_DUE_DATE = "Selecteer een vervaldatum!";
	String NO_INVOICE_HEADER = "Factuur bestaat nog niet! Klik \'OK\'";
	String INVOICE_UPDATE_NOT_ALLOWED = "Factuur kan niet meer gewijzigd worden!";
	String INVOICE_UPDATE_IMPOSSIBLE = "Update functie niet beschikbaar!";
	String INVOICE_DELETE_NOT_ALLOWED = "Factuur kan niet meer verwijderd worden!";
	String NO = "Neen";
	String YES = "Ja";
	String OK = "OK";
	String NO_QTY = "Hoeveelheid moet ingevuld zijn!";
	String NO_MEASURE = "Eenheid moet ingevuld zijn!";
	String WRONG_MEASURE = "Eenheid is niet correct!";
	String NO_PRODUCT = "Er moet een product geselecteerd worden!";
	String NO_PRICE = "Er moet een prijs geselecteerd worden!";
	String QTY_NO_FIGURES = "Hoeveelheid mag enkel cijfers bevatten!";
	String PRICE_NO_FIGURES = "Prijs mag enkel cijfers bevatten!";
	String ONLY_NUMBERS_ALLOWED = "Enkel cijfers zijn toegelaten!";
	String NO_DATE = "Selecteer een datum!";
	String NOT_ZERO_PRICE = "Prijs moet groter dan 0 zijn!";
	String NO_UNIT = "Selecteer een eenheid!";
	String PRICE_UPDATE_NOT_ALLOWED = "Prijs kan niet meer gewijzigd worden!'";
	String PRICE_DELETE_NOT_ALLOWED = "Prijs kan niet meer verwijderd worden!'";
	String NO_PRODUCT_CODE = "Productnaam moet ingevuld zijn!";
	String PRODUCT_CODE_EXIST = "Productnaam bestaat reeds!";
	String NO_PRODUCT_DESC = "Product omschrijving moet ingevuld zijn!";
	String NO_PRODUCT_CATEGORY = "Categorie moet ingevuld zijn!";
	String NO_PRODUCT_TYPE = "Type moet ingevuld zijn!";
	String DATE_FUTURE = "Datum moet in de toekomst liggen!";
	String QUOTE_UPDATE_NOT_ALLOWED = "Offerte kan niet meer gewijzigd worden!'";
	String QUOTE_UPDATE_IMPOSSIBLE = "Update functie niet beschikbaar!";
	String QUOTE_DELETE_NOT_ALLOWED = "Offerte kan niet meer verwijderd worden!'";
	String INVALID_DETAIL_LINE = "Detaillijn bevat fouten!";
	String VAT = "Vat";
	String PAYMENT_CONDITIONS = "Pay";
	String COMMENTS = "Credit nota voor Factuur: ";
	String DOCUMENT_ROOT = "user.home";
	String DOCUMENT_PATH = "documentPath";
	String SETTINGS_PATH = "resources/config/";
	String SETTINGS_FILE = "settings.properties";
	String IMG_RESOURCE = "resources/img/%s.bmp";
	String EXTENTION = ".pdf";
	String LOGO = "Logo";
	String EURO = "\u20AC";
	// public String DELIVERY_CONDITIONS =
	// "De door ons vastgestelde termijnen voor levering en uitvoering van de werken zijn benaderend en geenszins bindend. Bij een gebeurlijk vertraag kan onze onderneming nooit aansprakelijk gesteld worden voor enige schade. Er zal gefaktureerd worden naargelang het verloop van levering en de vordering van de werken. Klachten dienen binnen de 8 dagen te worden ingediend. Onze fakturen zijn betaalbaar op onze zetel te bree. Bij een wanbetaling op de vervaldag is er, zonder ingebrekestelling, een verwijlrente verschuldigd van 1,5% per maand alsmede een forfetaire schadevergoeding van 10%. Op straffe van verval dienen de klachten met een aangetekend schrijven ingediend te worden binnen de acht dagen na ontvangst factuur. Bij regelmatige en gerechtvaardigde klacht is onze onderneming enkel gehouden tot de vervanging van de gebrekkige goederen, op voorwaarde dat alle openstaande fakturen, ook deze die betrekking hebben op andere overeenkomsten, werden voldaan. Alle aanspraken van onze medecontractant zijn uitgesloten. Onze medecontractant is tot betaling gehouden niettegenstaand welke eis en welke aanspraken door deze ook worden gesteld. Elke verrekening is uitgesloten. Onze ondeneming is gerechtigd elke prestatie op te schorten zolang alle openstaande fakturen, ook deze die op andere overeenkomsten betrekking hebben, niet werden voldaan. Onze fakturen zijn betaalbaar op overeengekomen termijnen, vermeld in de bestelling. Indien geen specifieke termijn is vermeld zijn onze fakturen contant betaalbaar. Enkel de rechtbank van Tongeren zijn bevoegd.";
	String DELIVERY_CONDITIONS = "Facturen van iButeo dienen betaald te worden uiterlijk op de vermelde vervaldag. Bij gebrek aan onmiddellijke betaling zullen na de eerste aanmaning verwijlintresten a rato van de wettelijke rentevoeten verschuldigd zijn. In geval van een eerste aanmaning door iButeo is een bijkomende administratiekost verschuldigd van 5 euro. Vervolgens zal bij ontbreken van betaling het dossier onverwijld worden overgemaakt voor verdere invordering. Als een aangetekende ingebrekestelling door de gerechtsdeurwaarder of een gerechtelijke procedure noodzakelijk wordt om betaling te bekomen, zal er bijkomend een forfaitaire schadevergoeding van 10 procent van het factuurbedrag met een minimum van 40 euro aangerekend worden. Bij een gerechtelijke procedure zijn enkel de rechtbanken van Leuven bevoegd, in toepassing van artikel 624, 2� van het Gerechtelijk Wetboek.";
	String HEAD = "H";
	String CN_BTW_VERMINDERING = "BTW terug te storten in de mate waarin hij oorspronkelijk in mindering werd gebracht.";
	// public String CO_CONTRACTOR =
	// "BTW te voldoen door medecontractant art 20 KB.1";
	String CO_CONTRACTOR = "BTW verlegd, art. 5 , ontwerp KB nr. 1";
	String COUNTRY_CODE = "Cnt";
	String ZERO = "0.0";
	String _ZERO = "0";
	String BTW = "BTW";
	String BTW_ = "BTW: ";
	String BTW6 = "BTW 6%";
	String BTW21 = "BTW 21%";
	String BLANK = " ";
	String PERIOD = ", ";
	String DATE = "Datum";
	String TELEPHONE = "Tel: ";
	String MOBILE = "GSM: ";
	String FAX = "Fax: ";
	String REFERENCE_ = "Referentie";
	String SEPARATOR_FLAT = "_";
	String PERCENTAGE = "%";
	String INVOICE = "Factuur";
	String DETAILS = "Details";
	String REMOVE = "Verwijderen";
	String ENTERPRISE = "Ondernemersnummer :";
	String INVOICE_ADDRESS = "Facturatie adres :";
	String INVOICE_DATE = " Factuurdatum :";
	String INVOICE_NUMBER = "Nummer :";
	String INVOICE_DUEDATE = "Vervaldatum :";
	String INVOICE_DETAIL_LINES = "Factuur detaillijnen";
	String STATUS = "Status :";
	String CREDIT_NOTE = "Credit Nota";
	String INVOICE_DETAIL_PATH = "/facturen/";
	String INVOICE_TYPE = "I";
	String CREDITNOTE_TYPE = "C";
	String QUOTE_DETAIL_PATH = "/offertes/";
	String VOORLOPIG = "VH";
	String UNIT = "EH ";
	String URL = "url";
	String USER = "user";
	String PASSWORD = "password";
	String DRIVER = "driver";
	String INVOICE_DIALOG = "invoicedialog";
	String QUOTE_DIALOG = "quotedialog";
	String INVOICE_FREE = "FreeForm";
	String INVOICE_FIX = "FixForm";
	String QUOTE_FREE = "FreeForm";
	String QUOTE_FIX = "FixForm";
	String DOCUMENT_LINES = "documentlines";
	String COMMENT_FONT_SIZE = "commentfontsize";
	String SAVE = "Gegevens bewaren";
	String PRINT = "Afdrukken";
	String CONFIRM = "Bevestigen";
	String INVOICE_PRINT = "Factuur afdrukken";
	String QOUTE_PRINT = "Offerte afdrukken";
	String NEW = "Nieuw";
	String NEW_DETAIL = "Nieuwe detaillijn";
	String PAYMENT = "Betalen";
	String DELIVERY_ADDRESS = "Werf adress :";
	String ADDED = " werd Toegevoegd";
	String CHANGED = " werd gewijzigd";
	String LINE = "Lijn ";
	String LINE_LOWER_CASE = " lijn ";
	String LN = "Ln";
	String DESC = "Post";
	String QUANTITY = "Hoeveelheid";
	String PRICE = "Prijs";
	String TOTAL = "Totaal";
	String REFERENCE = "Referentie :";
	String TOTAL_EXCL = "Totaal (excl):";
	String TOTAL_INCL = "Totaal (incl):";
	String FONT_DIALOG = "Dialog";
	String FONT_COURIER_NEW = "Courier New";
	String GENERAL_PURPOSE = "Very long Product Description for general purposes";
	String DATE_FORMAT = "d MMM, yyyy";
}
