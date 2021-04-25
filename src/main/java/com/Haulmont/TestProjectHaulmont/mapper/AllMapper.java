package com.Haulmont.TestProjectHaulmont.mapper;

import com.Haulmont.TestProjectHaulmont.DTO.*;
import com.Haulmont.TestProjectHaulmont.model.*;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AllMapper extends ConfigurableMapper {
    protected void configure(MapperFactory factory) {
        factory.classMap(User.class, UserDTO.class)
                .field("bank.id", "bankId")
                .byDefault()
                .customize(new CustomMapper<User, UserDTO>() {
                    @Override
                    public void mapAtoB(User user, UserDTO userDTO, MappingContext context) {
                        if (user.getCreditOffer() != null) {
                            userDTO.getCreditOffer().setUser(null);
                        }
                        super.mapAtoB(user, userDTO, context);
                    }

                    @Override
                    public void mapBtoA(UserDTO userDTO, User user, MappingContext context) {
                        if (userDTO.getCreditOffer() != null) {
                            user.setCreditOffer(new CreditOffer(userDTO.getCreditOffer().getId()));
                        }
                        super.mapBtoA(userDTO, user, context);
                    }
                })
                .register();

        factory.classMap(Bank.class, BankDTO.class)
                .byDefault()
                .customize(new CustomMapper<Bank, BankDTO>() {
                    @Override
                    public void mapAtoB(Bank bank, BankDTO bankDTO, MappingContext context) {
                        if (bank.getCredits() != null) {
                            bankDTO.setCredits(mapperFacade.mapAsList(bank.getCredits(), CreditDTO.class));
                        }
                        if (bank.getUsers() != null) {
                            bankDTO.setUsers(mapperFacade.mapAsList(bank.getUsers(), UserDTO.class));
                        }
                        super.mapAtoB(bank, bankDTO, context);
                    }

                    @Override
                    public void mapBtoA(BankDTO bankDTO, Bank bank, MappingContext context) {
                        super.mapBtoA(bankDTO, bank, context);
                    }
                })
                .register();

        factory.classMap(Credit.class, CreditDTO.class)
                .field("bank.id", "bankId")
                .byDefault()
                .register();

        factory.classMap(CreditOffer.class, CreditOfferDTO.class)
                .byDefault()
                .customize(new CustomMapper<CreditOffer, CreditOfferDTO>() {
                    @Override
                    public void mapAtoB(CreditOffer creditOffer, CreditOfferDTO creditOfferDTO, MappingContext context) {
                        if (creditOffer.getUser() != null) {
                            creditOfferDTO.setCredit(mapperFacade.map(creditOffer.getCredit(), CreditDTO.class));
                            CreditOfferDTO creditOfferDTO1 = new CreditOfferDTO();
                            creditOfferDTO1.setId(creditOffer.getId());
                            creditOfferDTO.getUser().setCreditOffer(creditOfferDTO1);
                            creditOffer.getPaymentSchedules().forEach(paymentSchedule -> {
                                paymentSchedule.setCreditOffer(new CreditOffer(creditOffer.getId()));
                            });
                            creditOfferDTO.setPaymentSchedules(mapperFacade.mapAsList(creditOffer.getPaymentSchedules(), PaymentScheduleDTO.class));
                        }
                        super.mapAtoB(creditOffer, creditOfferDTO, context);
                    }

                    @Override
                    public void mapBtoA(CreditOfferDTO creditOfferDTO, CreditOffer creditOffer, MappingContext context) {
                        if (creditOfferDTO.getUser() != null) {
                            User user = new User(UUID.randomUUID().toString());
                            user.setName(creditOfferDTO.getUser().getName());
                            user.setEmail(creditOfferDTO.getUser().getEmail());
                            user.setPhone(creditOfferDTO.getUser().getPhone());
                            user.setBank(new Bank(creditOfferDTO.getCredit().getBankId()));
                            user.setNumberPassport(creditOfferDTO.getUser().getNumberPassport());
                            creditOffer.setUser(user);
                        }

                        if (creditOfferDTO.getSumCredit() != null && creditOfferDTO.getAmountMonth() != null) {
                            List<PaymentSchedule> paymentSchedules = new ArrayList<>();
                            Float sumCredit = creditOfferDTO.getSumCredit();
                            Float monthlyPayment = creditOfferDTO.getSumCredit()/creditOfferDTO.getAmountMonth();
                            Float perMonth = (creditOfferDTO.getCredit().getInteresRate()/100)/12;
                            System.out.println(perMonth);
                            for (int i = 0; i < creditOfferDTO.getAmountMonth(); i++) {
                                PaymentSchedule paymentSchedule = new PaymentSchedule();
                                paymentSchedule.setId(UUID.randomUUID().toString());
                                paymentSchedule.setPercentSum(sumCredit*perMonth);
                                paymentSchedule.setBodyCreditSum(monthlyPayment);
                                paymentSchedule.setPaymentSum(monthlyPayment+(sumCredit*perMonth));
                                paymentSchedule.setPaymentDate(LocalDate.now().plusMonths(i));
                                paymentSchedule.setCreditOffer(creditOffer);
                                sumCredit -= monthlyPayment;
                                paymentSchedules.add(paymentSchedule);
                            }
                            creditOffer.setPaymentSchedules(paymentSchedules);
                        }
                        super.mapBtoA(creditOfferDTO, creditOffer, context);
                    }
                })
                .register();

        factory.classMap(PaymentSchedule.class, PaymentScheduleDTO.class)
                .field("creditOffer.id", "creditOfferId")
                .byDefault()
                .register();
    }
}