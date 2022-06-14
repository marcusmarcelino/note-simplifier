package br.com.apinotesimplifier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.apinotesimplifier.enums.AccountstatusUser;
import br.com.apinotesimplifier.enums.SituationPaymentSale;
import br.com.apinotesimplifier.interfaces.PaymentMethodService;
import br.com.apinotesimplifier.interfaces.ProductService;
import br.com.apinotesimplifier.interfaces.RoleService;
import br.com.apinotesimplifier.interfaces.SaleService;
import br.com.apinotesimplifier.interfaces.ServiceProvidedService;
import br.com.apinotesimplifier.interfaces.UserService;
import br.com.apinotesimplifier.models.Address;
import br.com.apinotesimplifier.models.PaymentForServiceProvided;
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

	// @Bean
	CommandLineRunner run(
			UserService userService,
			RoleService roleService,
			ProductService productService,
			SaleService saleService,
			PaymentMethodService payMethService,
			ServiceProvidedService serviceProvidedService) {
		return args -> {
			roleService.saveRole(new Role(null, "ROLE_USER"));
			roleService.saveRole(new Role(null, "ROLE_ADMIN"));
			roleService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
			roleService.saveRole(new Role(null, "ROLE_SELLER"));
			roleService.saveRole(new Role(null, "ROLE_PROFESSIONAL"));

			User normal_user = new User();
			normal_user.setUsername("normal_user");
			normal_user.setPassword("password");
			normal_user.setProfession("Usuário Normal");
			normal_user.setAccountStatus(AccountstatusUser.active);
			Address address_normal_user = new Address();
			address_normal_user.setDistrict("Plan. Dir. Sul");
			address_normal_user.setRoad("NS2");
			address_normal_user.setNumber("03");
			address_normal_user.setComplement("Condomínio Palmeira Dourada");
			address_normal_user.setCity("Palmas");
			address_normal_user.setUf("TO");
			PersonalData data_normal_user = new PersonalData();
			data_normal_user.setName("Usuário normal");
			data_normal_user.setEmail("normal_user@mail.com");
			data_normal_user.setContact("63985001122");
			data_normal_user.setAddresses(Arrays.asList(address_normal_user));
			normal_user.setIdPersonalData(data_normal_user);
			userService.save(normal_user);

			User user_admin = new User();
			user_admin.setUsername("admin_user");
			user_admin.setPassword("password");
			user_admin.setProfession("Admin");
			user_admin.setAccountStatus(AccountstatusUser.active);
			Address address_user_admin = new Address();
			address_user_admin.setDistrict("Plan. Dir. Sul");
			address_user_admin.setRoad("NS2");
			address_user_admin.setNumber("03");
			address_user_admin.setComplement("Condomínio Palmeira Monarca");
			address_user_admin.setCity("Palmas");
			address_user_admin.setUf("TO");
			PersonalData data_user_admin = new PersonalData();
			data_user_admin.setName("Usuário Admin");
			data_user_admin.setEmail("admin_user@mail.com");
			data_user_admin.setContact("63985002233");
			data_user_admin.setAddresses(Arrays.asList(address_user_admin));
			user_admin.setIdPersonalData(data_user_admin);
			userService.save(user_admin);

			User user_super_admin = new User();
			user_super_admin.setUsername("super_admin_user");
			user_super_admin.setPassword("password");
			user_super_admin.setProfession("Super admin");
			user_super_admin.setAccountStatus(AccountstatusUser.active);
			Address address_user_super_admin = new Address();
			address_user_super_admin.setDistrict("Plan. Dir. Sul");
			address_user_super_admin.setRoad("NS2");
			address_user_super_admin.setNumber("03");
			address_user_super_admin.setComplement("Condomínio Porto Seguro");
			address_user_super_admin.setCity("Brasília");
			address_user_super_admin.setUf("DF");
			PersonalData data_user_super_admin = new PersonalData();
			data_user_super_admin.setName("Usuário Super Admin");
			data_user_super_admin.setEmail("super_admin_user@mail.com");
			data_user_super_admin.setContact("63985003344");
			data_user_super_admin.setAddresses(Arrays.asList(address_user_super_admin));
			user_super_admin.setIdPersonalData(data_user_super_admin);
			userService.save(user_super_admin);

			User user_seller = new User();
			user_seller.setUsername("profissional_user_seller");
			user_seller.setPassword("password");
			user_seller.setProfession("Seller");
			user_seller.setAccountStatus(AccountstatusUser.active);
			Address address_user_seller = new Address();
			address_user_seller.setDistrict("Plan. Dir. Sul");
			address_user_seller.setRoad("NS2");
			address_user_seller.setNumber("03");
			address_user_seller.setComplement("Condomínio Águas do Vale");
			address_user_seller.setCity("São Paulo");
			address_user_seller.setUf("SP");
			PersonalData data_user_seller = new PersonalData();
			data_user_seller.setName("Usuário Profissional");
			data_user_seller.setEmail("seller_profissional_user@mail.com");
			data_user_seller.setContact("63985004455");
			data_user_seller.setAddresses(Arrays.asList(address_user_seller));
			user_seller.setIdPersonalData(data_user_seller);
			userService.save(user_seller);

			User professional_user_provider = new User();
			professional_user_provider.setUsername("professional_user_provider");
			professional_user_provider.setPassword("password");
			professional_user_provider.setProfession("Provider");
			professional_user_provider.setAccountStatus(AccountstatusUser.active);
			Address address_professional_user_provider = new Address();
			address_professional_user_provider.setDistrict("Plan. Dir. Sul");
			address_professional_user_provider.setRoad("MS2");
			address_professional_user_provider.setNumber("03");
			address_professional_user_provider.setComplement("Condomínio Vale Verde");
			address_professional_user_provider.setCity("Paraíso");
			address_professional_user_provider.setUf("TO");
			PersonalData data_professional_provider = new PersonalData();
			data_professional_provider.setName("Usuário Profissional Prestador de Serviços");
			data_professional_provider.setEmail("professional_user_provider@mail.com");
			data_professional_provider.setContact("63985005566");
			data_professional_provider.setAddresses(Arrays.asList(address_professional_user_provider));
			professional_user_provider.setIdPersonalData(data_professional_provider);
			userService.save(professional_user_provider);

			userService.addRoleToUser("normal_user", "ROLE_USER");
			userService.addRoleToUser("admin_user", "ROLE_ADMIN");
			userService.addRoleToUser("super_admin_user", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("profissional_user_seller", "ROLE_SELLER");
			userService.addRoleToUser("professional_user_provider", "ROLE_PROFESSIONAL");

			PaymentMethod payMeth_money = payMethService.save(new PaymentMethod(null, "MONEY", "DINHEIRO AVISTA"));
			PaymentMethod payMeth_cred = payMethService.save(new PaymentMethod(null, "CREDIT_SPOTS", "CREDITO AVISTA"));
			payMethService.save(new PaymentMethod(null, "CREDIT_IN_INSTALLMENTS", "CREDITO PARCELADO"));

			Product product1 = new Product();
			product1.setName("product1");
			product1.setDescription("Produto 1");
			product1.setType("UN");
			product1.setEan("19922811");
			product1.setEanMain("21941");
			product1.setVlUnitary(BigDecimal.valueOf(20));
			product1.setQuantityPerBox(1);
			productService.save(product1);

			Product product2 = new Product();
			product2.setName("produto2");
			product2.setDescription("Produto 2");
			product2.setType("CX");
			product2.setEan("19922866");
			product2.setEanMain("21956");
			product2.setVlUnitary(BigDecimal.valueOf(50));
			product2.setQuantityPerBox(5);
			productService.save(product2);

			Product product3 = new Product();
			product3.setName("produto3");
			product3.setDescription("Produto 3");
			product3.setType("UN");
			product3.setEan("19922855");
			product3.setEanMain("21976");
			product3.setVlUnitary(BigDecimal.valueOf(70));
			product3.setQuantityPerBox(1);
			productService.save(product3);

			Product product4 = new Product();
			product4.setName("produto4");
			product4.setDescription("Produto 4");
			product4.setType("CX");
			product4.setEan("19922833");
			product4.setEanMain("21984");
			product4.setVlUnitary(BigDecimal.valueOf(20));
			product4.setQuantityPerBox(4);
			productService.save(product4);

			SalePayment salePayment = new SalePayment();
			salePayment.setPaymentMethods(Arrays.asList(payMeth_money, payMeth_cred));
			salePayment.setInstallmentForm(1);
			salePayment.setTotal(BigDecimal.valueOf(1000));
			salePayment.setPayday(LocalDate.now());
			salePayment.setIdSale(null);
			salePayment.setSituation(SituationPaymentSale.PROCESSING);
			salePayment.setDate(LocalDate.now());

			Sale sale = new Sale();
			sale.setIdClient(new User(Long.valueOf(1)));
			sale.setIdSeller(new User(Long.valueOf(4)));
			sale.setIdSalePayment(salePayment);
			sale.setSituation("IN_PROGRESS");
			sale.setDateOfSale(LocalDate.now());
			sale.setVlTotal(BigDecimal.valueOf(1000));

			SellItem item1 = new SellItem(null, product1, 2, sale, product1.getVlUnitary(),
					product1.getVlUnitary().multiply(BigDecimal.valueOf(2)));
			SellItem item2 = new SellItem(null, product2, 2, sale, product1.getVlUnitary(),
					product2.getVlUnitary().multiply(BigDecimal.valueOf(2)));
			SellItem item3 = new SellItem(null, product3, 2, sale, product1.getVlUnitary(),
					product3.getVlUnitary().multiply(BigDecimal.valueOf(2)));
			SellItem item4 = new SellItem(null, product4, 2, sale, product1.getVlUnitary(),
					product4.getVlUnitary().multiply(BigDecimal.valueOf(2)));

			sale.setSellItems(Arrays.asList(item1, item2, item3, item4));
			saleService.save(sale);

			PaymentForServiceProvided paymentForServiceProvided = new PaymentForServiceProvided();
			paymentForServiceProvided.setPaymentMethods(Arrays.asList(payMeth_cred));
			paymentForServiceProvided.setInstallmentForm(2);
			paymentForServiceProvided.setAmount(BigDecimal.valueOf(1000));
			paymentForServiceProvided.setPayday(LocalDate.now());
			paymentForServiceProvided.setIdServiceProvided(null);
			paymentForServiceProvided.setSituation("PROCESSING");
			paymentForServiceProvided.setDate(LocalDate.now());

			ServiceProvided serviceProvided = new ServiceProvided();
			serviceProvided.setServiceDescription("Serviço realizado");
			serviceProvided.setSituation("IN_PROGRESS");
			serviceProvided.setIdProfessional(new User(Long.valueOf(5)));
			serviceProvided.setIdClient(new User(Long.valueOf(1)));
			serviceProvided.setServiceDate(LocalDate.now());
			serviceProvided.setVlTotal(BigDecimal.valueOf(1000));
			serviceProvided.setIdPaymentForServiceProvided(paymentForServiceProvided);

			serviceProvidedService.save(serviceProvided);
		};
	}
}