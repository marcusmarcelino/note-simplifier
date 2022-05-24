package br.com.apinotesimplifier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.apinotesimplifier.dto.SellItemDTO;
import br.com.apinotesimplifier.interfaces.PaymentMethodService;
import br.com.apinotesimplifier.interfaces.ProductService;
import br.com.apinotesimplifier.interfaces.RoleService;
import br.com.apinotesimplifier.interfaces.SaleService;
import br.com.apinotesimplifier.interfaces.SellItemService;
import br.com.apinotesimplifier.interfaces.ServiceProvidedService;
import br.com.apinotesimplifier.interfaces.UserAndPersonalData;
import br.com.apinotesimplifier.interfaces.UserService;
import br.com.apinotesimplifier.models.PaymentMethod;
import br.com.apinotesimplifier.models.PersonalData;
import br.com.apinotesimplifier.models.Product;
import br.com.apinotesimplifier.models.Role;
import br.com.apinotesimplifier.models.Sale;
import br.com.apinotesimplifier.models.SalePayment;
import br.com.apinotesimplifier.models.SellItem;
import br.com.apinotesimplifier.models.ServiceProvided;
import br.com.apinotesimplifier.models.User;

@SpringBootApplication
public class NoteSimplifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteSimplifierApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(
			UserService userService,
			RoleService roleService,
			ProductService productService,
			SaleService saleService,
			PaymentMethodService payMethService,
			SellItemService sellItemService,
			ServiceProvidedService serviceProvidedService) {
		return args -> {
			roleService.saveRole(new Role(null, "ROLE_USER"));
			roleService.saveRole(new Role(null, "ROLE_ADMIN"));
			roleService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
			roleService.saveRole(new Role(null, "ROLE_SELLER"));
			roleService.saveRole(new Role(null, "ROLE_PROFESSIONAL"));

			userService.save(new UserAndPersonalData(
					new User(null, "normal_user", "password", "", new ArrayList<>(), "active", null),
					new PersonalData(null, "Usuário Normal", "normal_user@mail.com", "63985001122",
							"Plan. Dir. Sul", "NS2", "01", "Residencial Pal. Real", "Palmas", "TO")));

			userService.save(new UserAndPersonalData(
					new User(null, "admin_user", "password", "", new ArrayList<>(), "active", null),
					new PersonalData(null, "Usuário Admin", "admin_user@mail.com", "63985002233",
							"Plan. Dir. Sul", "NS2", "01", "Residencial Pal. Real", "Palmas", "TO")));

			userService.save(new UserAndPersonalData(
					new User(null, "super_admin_user", "password", "", new ArrayList<>(), "active", null),
					new PersonalData(null, "Usuário Super Admin", "super_admin_user@mail.com", "63985003344",
							"Plan. Dir. Sul", "NS2", "01", "Residencial Pal. Real", "Palmas", "TO")));

			userService.save(new UserAndPersonalData(
					new User(null, "profissional_user_vendedor", "password", "", new ArrayList<>(), "active", null),
					new PersonalData(null, "Usuário Profissional", "profissional_user_vendedor@mail.com", "63985004455",
							"Plan. Dir. Sul", "NS2", "01", "Residencial Pal. Real", "Palmas", "TO")));

			userService.save(new UserAndPersonalData(
					new User(null, "profissional_user_prestador", "password", "", new ArrayList<>(), "active", null),
					new PersonalData(null, "Usuário Profissional", "profissional_user_prestador@mail.com", "63985005566",
							"Plan. Dir. Sul", "NS2", "01", "Residencial Pal. Real", "Palmas", "TO")));

			userService.addRoleToUser("normal_user", "ROLE_USER");
			userService.addRoleToUser("admin_user", "ROLE_ADMIN");
			userService.addRoleToUser("super_admin_user", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("profissional_user_vendedor", "ROLE_SELLER");
			userService.addRoleToUser("profissional_user_prestador", "ROLE_PROFESSIONAL");

			PaymentMethod payMeth_money = payMethService.save(new PaymentMethod(null, "MONEY", "DINHEIRO AVISTA"));
			// PaymentMethod payMeth_cred = payMethService.save(new PaymentMethod(null,
			// "CREDIT_SPOTS", "CREDITO AVISTA"));
			// PaymentMethod payMeth_cred_inst = payMethService.save(new PaymentMethod(null,
			// "CREDIT_IN_INSTALLMENTS", "CREDITO PARCELADO"));

			List<Long> paymentMethods = new ArrayList<>();
			paymentMethods.add(payMeth_money.getId());

			Product product1 = productService
					.save(new Product(null, "produto1", "Produto 1", "UN", "19922899", "21998", BigDecimal.valueOf(100)));
			Product product2 = productService
					.save(new Product(null, "produto2", "Produto 2", "CX", "19922866", "21956", BigDecimal.valueOf(50)));
			Product product3 = productService
					.save(new Product(null, "produto3", "Produto 3", "UN", "19922855", "21976", BigDecimal.valueOf(70)));
			Product product4 = productService
					.save(new Product(null, "produto4", "Produto 4", "CX", "19922833", "21984", BigDecimal.valueOf(20)));

			SalePayment salePayment = new SalePayment(null, new ArrayList<>(), 1, BigDecimal.valueOf(1000), LocalDate.now(),
					null, "EM PROCESSAMENTO", LocalDate.now());
			Sale sale = new Sale(null, null, null, new ArrayList<>(), salePayment, LocalDate.now());
			Sale saleCreated = saleService.saveWithIds(sale, Long.valueOf(4), Long.valueOf(1), paymentMethods);

			List<SellItem> sellItems = new ArrayList<>();
			SellItem sellItem1 = sellItemService
					.saveWithIds(new SellItemDTO(null, product1.getId(), product1.getName(), 2, sale.getId()));
			SellItem sellItem2 = sellItemService
					.saveWithIds(new SellItemDTO(null, product2.getId(), product1.getName(), 2, sale.getId()));
			SellItem sellItem3 = sellItemService
					.saveWithIds(new SellItemDTO(null, product3.getId(), product1.getName(), 2, sale.getId()));
			SellItem sellItem4 = sellItemService
					.saveWithIds(new SellItemDTO(null, product4.getId(), product1.getName(), 2, sale.getId()));
			sellItems.add(sellItem1);
			sellItems.add(sellItem2);
			sellItems.add(sellItem3);
			sellItems.add(sellItem4);
			saleCreated.setSellItems(sellItems);

			serviceProvidedService.saveServiceprovidedWithIds(new ServiceProvided(null, null, null, "",
					null, LocalDate.now()), Long.valueOf(5), Long.valueOf(1));
		};
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages"); // /META-INF/messages
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	// private static final ResourceBundle messagesRB =
	// ResourceBundle.getBundle("/META-INF/messages.properties");

	// public static String getMessage(String mensagem) {
	// if (messagesRB.containsKey(mensagem)) {
	// return messagesRB.getString(mensagem);
	// }
	// return "";
	// }
}
